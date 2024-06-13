# Getting Started - 필수 사항 알아보기



## Ehcache 구성하기

Ehcache 사용을 시작하려면 첫 번째 `CacheManager`와 `Cache`를 구성해야 합니다. 이는 [프로그래밍 방식 구성](https://www.ehcache.org/documentation/3.10/getting-started.html#configuring-with-java) 또는 [XML](https://www.ehcache.org/documentation/3.10/getting-started.html#configuring-with-xml)을 통해 달성할 수 있습니다.

| i    | JSR-107, 일명 `javax.cache` API를 사용하려는 경우 [Ehcache 3.x JSR-107 공급자 페이지](https://www.ehcache.org/documentation/3.10/107.html)부터 읽어야 합니다. |
| ---- | ------------------------------------------------------------ |
|      |                                                              |



### 프로그래밍 방식 구성

Java 구성은 유창한 API를 제공하는 빌더를 사용하여 가장 쉽게 수행됩니다.

이전 버전의 Ehcache와 마찬가지로 `Cache`를 처리하는 정식 방법은 `CacheManager`를 사용하는 것입니다.

```java
CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()  // (1)
    .withCache("preConfigured",
        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10))) // (2)
    .build(); // (3)
cacheManager.init();  // (4)

Cache<Long, String> preConfigured =
    cacheManager.getCache("preConfigured", Long.class, String.class); 

Cache<Long, String> myCache = cacheManager.createCache("myCache", 
    CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10)));

myCache.put(1L, "da one!"); 
String value = myCache.get(1L); 

cacheManager.removeCache("preConfigured"); 

cacheManager.close(); 
```

|      | The static method `org.ehcache.config.builders.CacheManagerBuilder.newCacheManagerBuilder` returns a new `org.ehcache.config.builders.CacheManagerBuilder` instance. |
| ---- | ------------------------------------------------------------ |
|      | Use the builder to define a `Cache` with alias "preConfigured". This cache will be created when `cacheManager.build()` is invoked on the actual `CacheManager` instance. The first `String` argument is the cache alias, which is used to retrieve the cache from the `CacheManager`. The second argument, `org.ehcache.config.CacheConfiguration`, is used to configure the `Cache`. We use the static `newCacheConfigurationBuilder()` method on `org.ehcache.config.builders.CacheConfigurationBuilder` to create a default configuration. |
|      | Finally, invoking `build()` returns a fully instantiated, **but uninitialized**, `CacheManager` we can use. |
|      | Before using the `CacheManager` it needs to be initialized, which can be done in 1 of 2 ways: Calling `CacheManager.init()` on the `CacheManager` instance, or calling the `CacheManagerBuilder.build(boolean init)` method with the boolean parameter set to true. |
|      | A cache is retrieved by passing its alias, key type and value type to the `CacheManager`. For instance, to obtain the cache declared in step 2 you need its `alias="preConfigured"`, `keyType=Long.class` and `valueType=String.class`. For type-safety, we ask for both key and value types to be passed in. If these differ from the ones we expect, the `CacheManager` throws a `ClassCastException` early in the application’s lifecycle. This guards the `Cache` from being polluted by random types. |
|      | The `CacheManager` can be used to create new `Cache` instances as needed. Just as in step 2, it requires passing in an alias as well as a `CacheConfiguration`. The instantiated and fully initialized `Cache` added will be returned and/or accessed through the `CacheManager.getCache` API. |
|      | The newly added `Cache` can now be used to store entries, which are comprised of key value pairs. The put method’s first parameter is the key and the second parameter is the value. Remember the key and value types must be the same types as those defined in the `CacheConfiguration`. Additionally the key must be unique and is only associated with one value. |
|      | A value is retrieved from a cache by calling the `cache.get(key)` method. It only takes one parameter which is the key, and returns the value associated with that key. If there is no value associated with that key then null is returned. |
|      | We can `CacheManager.removeCache(String)` a given `Cache`. The `CacheManager` will not only remove its reference to the `Cache`, but will also close it. The `Cache` releases all locally held transient resources (such as memory). References to this `Cache` become unusable. |
|      | In order to release all transient resources (memory, threads, …) a `CacheManager` provides to `Cache` instances it manages, you have to invoke `CacheManager.close()`, which in turns closes all `Cache` instances known at the time. |

Here is a shorter version featuring 3 important things:

```
try(CacheManager cacheManager = newCacheManagerBuilder() 
  .withCache("preConfigured", newCacheConfigurationBuilder(Long.class, String.class, heap(10))) 
  .build(true)) { 

  // Same code as before [...]
}
```

|      | A `CacheManager` implements `Closeable` so can be closed automatically by a try-with-resources. A `CacheManager` must be closed cleanly. In a `finally` block, with a `try-with-resources` or (more frequent for normal applications) in some shutdown hook. |
| ---- | ------------------------------------------------------------ |
|      | Builders having different names, you can use static imports for all of them. |
|      | The `CacheManager` is initialized using `build(true)`.       |

### XML configuration

You can create an XML file to configure a `CacheManager`.

```
<config xmlns='http://www.ehcache.org/v3'>

  <cache alias="foo"> 
    <key-type>java.lang.String</key-type> 
    <value-type>java.lang.String</value-type> 
    <resources>
      <heap unit="entries">20</heap> 
      <offheap unit="MB">10</offheap> 
    </resources>
  </cache>

  <cache-template name="myDefaults"> 
    <key-type>java.lang.Long</key-type>
    <value-type>java.lang.String</value-type>
    <heap unit="entries">200</heap>
  </cache-template>

  <cache alias="bar" uses-template="myDefaults"> 
    <key-type>java.lang.Number</key-type>
  </cache>

  <cache alias="simpleCache" uses-template="myDefaults" /> 

</config>
```

|      | Declares a `Cache` aliased to `foo`.                         |
| ---- | ------------------------------------------------------------ |
|      | The keys and values of `foo` are declared as type `String`; if not specified, the default is `java.lang.Object`. |
|      | `foo` is declared to hold up to 2,000 entries on heap…       |
|      | …as well as up to 500 MB of off-heap memory before it starts evicting |
|      | `<cache-template>` elements let you create an abstract configuration that further `<cache>` configurations can then "extend" |
|      | `bar` is such a `Cache`. `bar` uses the `<cache-template>` named `myDefaults` and overrides its `key-type` to a wider type. |
|      | `simpleCache` is another such `Cache`. It uses `myDefaults` configuration for its sole `CacheConfiguration`. |

Refer to the [XML documentation](https://www.ehcache.org/documentation/3.10/xml.html) for more details on the XML format.

In order to parse an XML configuration, you can use the `XmlConfiguration` type:

```
URL myUrl = getClass().getResource("/my-config.xml"); 
Configuration xmlConfig = new XmlConfiguration(myUrl); 
CacheManager myCacheManager = CacheManagerBuilder.newCacheManager(xmlConfig); 
```

|      | Obtain a `URL` to your XML file location                     |
| ---- | ------------------------------------------------------------ |
|      | Instantiate an `XmlConfiguration` passing the XML file URL to it |
|      | Using the static `org.ehcache.config.builders.CacheManagerBuilder.newCacheManager(org.ehcache.config.Configuration)` lets you create your `CacheManager` instance using the `Configuration` from the `XmlConfiguration` |

### Creating a cache manager with clustering support

To enable Clustering with Terracotta, firstly you will have to [start the Terracotta server](https://www.ehcache.org/documentation/3.10/clustered-cache.html#starting-server) configured with clustered storage. In addition, for creating the cache manager with clustering support, you will need to provide the clustering service configuration:

```
CacheManagerBuilder<PersistentCacheManager> clusteredCacheManagerBuilder =
    CacheManagerBuilder.newCacheManagerBuilder() 
        .with(ClusteringServiceConfigurationBuilder.cluster(URI.create("terracotta://localhost/my-application")) 
            .autoCreateOnReconnect(c -> c)); 
PersistentCacheManager cacheManager = clusteredCacheManagerBuilder.build(true); 

cacheManager.close(); 
```

|      | Returns the `org.ehcache.config.builders.CacheManagerBuilder` instance; |
| ---- | ------------------------------------------------------------ |
|      | Use the `ClusteringServiceConfigurationBuilder`'s static method `.cluster(URI)` for connecting the cache manager to the clustering storage at the URI specified that returns the clustering service configuration builder instance. The sample URI provided in the example points to the clustered storage with clustered storage identifier **my-application** on the Terracotta server (assuming the server is running on localhost and port **9410**); the query-param `auto-create` creates the clustered storage in the server if it doesn’t already exist. |
|      | Returns a fully initialized cache manager that can be used to create clustered caches. |
|      | Auto-create the clustered storage if it doesn’t already exist. |
|      | Close the cache manager.                                     |

|      | See [the clustered cache documentation](https://www.ehcache.org/documentation/3.10/clustered-cache.html) for more information on this feature. |
| ---- | ------------------------------------------------------------ |
|      |                                                              |

### Storage Tiers

Ehcache 3, as in previous versions, offers a tiering model to allow storing increasing amounts of data on slower tiers (which are generally more abundant).

The idea is that resources related to faster storage are more rare, but are located where the 'hottest' data is preferred to be. Thus less-hot (less frequently used) data is moved to the more abundant but slower tiers. Hotter data is moved onto the faster tiers.

#### Three tiers

A classical example would be using 3 tiers with a persistent disk storage.

```
PersistentCacheManager persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
    .with(CacheManagerBuilder.persistence(new File(getStoragePath(), "myData"))) 
    .withCache("threeTieredCache",
        CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
            ResourcePoolsBuilder.newResourcePoolsBuilder()
                .heap(10, EntryUnit.ENTRIES) 
                .offheap(1, MemoryUnit.MB) 
                .disk(20, MemoryUnit.MB, true) 
            )
    ).build(true);

Cache<Long, String> threeTieredCache = persistentCacheManager.getCache("threeTieredCache", Long.class, String.class);
threeTieredCache.put(1L, "stillAvailableAfterRestart"); 

persistentCacheManager.close();
```

|      | If you wish to use disk storage (like for persistent `Cache` instances), you’ll have to provide a location where data should be stored on disk to the `CacheManagerBuilder.persistence()` static method. |
| ---- | ------------------------------------------------------------ |
|      | You define a resource pool for the heap. This will be your faster but smaller pool. |
|      | You define a resource pool for the off-heap. Still pretty fast and a bit bigger. |
|      | You define a persistent resource pool for the disk. It is persistent because said it should be (last parameter is `true`). |
|      | All values stored in the cache will be available after a JVM restart (assuming the `CacheManager` has been closed cleanly by calling `close()`). |

For details, read the [tiering documentation](https://www.ehcache.org/documentation/3.10/tiering.html).

### Data freshness

In Ehcache, data freshness is controlled through `Expiry`. The following illustrates how to configure a *time-to-live* expiry.

```
CacheConfiguration<Long, String> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, String.class,
        ResourcePoolsBuilder.heap(100)) 
    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(20))) 
    .build();
```

|      | Expiry is configured at the cache level, so start by defining a cache configuration, |
| ---- | ------------------------------------------------------------ |
|      | then add to it an `Expiry`, here using the predefined *time-to-live* one, configured with the required `Duration`. |

See the section on [expiry](https://www.ehcache.org/documentation/3.10/expiry.html) for more information about the options available.

## Advanced topics

A number of advanced topics that were discussed here have been moved out, see below.

### User managed cache

See [the user managed cache documentation](https://www.ehcache.org/documentation/3.10/usermanaged.html) for more information on this feature.

### Byte-sized heap

See the [relevant section](https://www.ehcache.org/documentation/3.10/tiering.html#byte-sized-heap) in the tiering documentation for more information on this feature.

### Update ResourcePools

See the [relevant section](https://www.ehcache.org/documentation/3.10/tiering.html#update-resourcepools) in the tiering documentation for more information on this feature.

### Off-heap

See [the tiering documentation](https://www.ehcache.org/documentation/3.10/tiering.html#off-heap) for more information on this feature.

### Disk persistence

See [the tiering documentation](https://www.ehcache.org/documentation/3.10/tiering.html#disk) for more information on this feature.