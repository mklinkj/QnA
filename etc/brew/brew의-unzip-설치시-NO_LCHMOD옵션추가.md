# brew로 unzip 설치시 -DNO_LCHMOD 옵션 추가

> brew의 깃에 질문 올렸음.... 😅
>
> * https://github.com/orgs/Homebrew/discussions/4445

# 문의

안녕하세요.

Windows WSL2 Ubuntu 환경에서 brew를 사용하고 있습니다.

그리고 brew를 통해 unzip도 설치되어있습니다.

이런 환경에서,  [SDKMan](https://sdkman.io/)을 사용해서 Java 17 Temurin을 설치하면 항상 아래의 오류가 나타납니다.

```
...
lchmod (file attributes) error: Operation not supported
lchmod (file attributes) error: Operation not supported
lchmod (file attributes) error: Operation not supported
...
```



어차피 Ubuntu 환경에서는 lchmod를 사용할 수 없다고 해서,  로컬환경의 brew의 파일을 수정했습니다.

* /home/linuxbrew/.linuxbrew/Homebrew/Library/Taps/homebrew/homebrew-core/Formula/unzip.rb

  ```ruby
  def install
      # These macros also follow Ubuntu, and are required:
      # - to correctly handle large archives (> 4GB)
      # - extract & print archive contents with non-latin characters
      loc_macros = %w[
        -DLARGE_FILE_SUPPORT
        -DUNICODE_SUPPORT
        -DUNICODE_WCHAR
        -DUTF8_MAYBE_NATIVE
        -DNO_WORKING_ISPRINT
        -DNO_LCHMOD     
      ]    
  ```
  
  * `-DNO_LCHMOD` 라는 매크로를 마지막에 추가했습니다.
* https://github.com/Homebrew/homebrew-core/blob/master/Formula/unzip.rb

위와 같이 매크로를 추가한 후, `unzip`을 삭제하고 수정된 `unzip.rb`로 다시 설치했습니다.

```sh
brew uninstall --ignore-dependencies unzip

brew install --build-from-source /home/linuxbrew/.linuxbrew/Homebrew/Library/Taps/homebrew/homebrew-core/Formula/unzip.rb
```



이렇게 한 상태에서 SDKMan을 통해 java 17 temurin을 설치하면 더이상 오류가 발생하지 않았습니다.

실행환경이 linux라면 조건부로 `-DNO_LCHMOD` 옵션이 포함되면 좋을 것 같은데, 괜찮을까요?



감사합니다.



## 문의 - 영문

## Add -DNO_LCHMOD macro option to unzip.rb



Hello.

I am using `brew` in Windows WSL2 Ubuntu environment and I also installed `unzip` using `brew`.

In such an environment, installing Java 17 Temurin using [[SDKMan](https://sdkman.io/)](https://sdkman.io/) always gives the error below.

```
...
lchmod (file attributes) error: Operation not supported
lchmod (file attributes) error: Operation not supported
lchmod (file attributes) error: Operation not supported
...
```

It was said that lchmod could not be used in the Ubuntu environment anyway, so I modified the `brew` file in the local environment.

* /home/linuxbrew/.linuxbrew/Homebrew/Library/Taps/homebrew/homebrew-core/Formula/unzip.rb

  ```ruby
    def install
      # These macros also follow Ubuntu, and are required:
      # - to correctly handle large archives (> 4GB)
      # - extract & print archive contents with non-latin characters
      loc_macros = %w[
        -DLARGE_FILE_SUPPORT
        -DUNICODE_SUPPORT
        -DUNICODE_WCHAR
        -DUTF8_MAYBE_NATIVE
        -DNO_WORKING_ISPRINT
        -DNO_LCHMOD     
      ]
  ```

  * I added the `-DNO_LCHMOD` macro at the end.
  * https://github.com/Homebrew/homebrew-core/blob/master/Formula/unzip.rb

After adding the macro as above, I deleted the `unzip` and reinstalled it with the modified `unzip.rb`.

```sh
brew uninstall --ignore-dependencies unzip

brew install --build-from-source /home/linuxbrew/.linuxbrew/Homebrew/Library/Taps/homebrew/homebrew-core/Formula/unzip.rb
```



Installing Java 17 Temurin using SDKMan no longer caused any errors.

Is it okay to use it like this?

I thought it would be nice to conditionally include the `-DNO_LCHMOD` macro on Linux systems that don't support `lchmod`... I'm not sure if that's a good idea.

Thanks.



## AI와 대화

> 현재 디렉토리이하에서 심볼릭 링크만 ls 하는 방법
>
> > `find` 명령어를 사용하여 현재 디렉토리 이하에서 심볼릭 링크인 파일만 찾을 수 있습니다. 다음 명령어를 사용하면 됩니다:
> >
> > ```
> > find . -type l -ls
> > ```
> >
> > 이 명령어는 현재 디렉토리(`.`) 이하에서 파일 유형이 심볼릭 링크(`-type l`)인 파일을 찾아서(`-ls`) 출력합니다.



### Temurin 원본 경로

* https://github.com/adoptium/temurin17-binaries/releases/download/jdk-17.0.7%2B7/OpenJDK17U-jdk_x64_linux_hotspot_17.0.7_7.tar.gz
  * 이미 심볼릭 링크가 많이 포함되어있다.
  * Ubuntu 환경에서 심볼릭 링크 같은 경우는 압축을 푸는 현재 계정소유자로 lrwxrwxrwx(777) 권한으로 처리되는 것 같다.