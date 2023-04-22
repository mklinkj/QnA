# React Router DOM 튜토리얼 따라하기

> * https://reactrouter.com/en/main/start/tutorial



## 튜토리얼

튜토리얼에 오신 것을 환영합니다! 연락처를 추적할 수 있는 작지만 기능이 풍부한 앱을 만들어 보겠습니다. 이 과정을 따라가다 보면 30~60분 정도 소요될 것으로 예상됩니다.

![튜토리얼 예제](https://reactrouter.com/_docs/tutorial/15.webp)

**👉 이 표시가 나타날 때마다 앱에서 무언가를 해야 한다는 뜻입니다!**

나머지는 정보 제공과 이해를 돕기 위한 것입니다. 그럼 시작해 보겠습니다.



### 설치

> 🎈 알림
>
> 여러분의 자체 앱에서 따라하지 않을 경우 이 섹션을 건너뛸 수 있습니다.

이 튜토리얼에서는 번들러와 개발 서버로 [Vite](https://vitejs.dev/guide/)를 사용하겠습니다. npm 명령줄 도구를 사용하려면 [Node.js](https://nodejs.org/)가 설치되어 있어야 합니다.

**👉️ 터미널을 열고 Vite로 새 React 앱을 부트스트랩하세요:**

```sh
> npm create vite@latest name-of-your-project -- --template react
Need to install the following packages:
  create-vite@4.3.0
Ok to proceed? (y) y
...
> cd my-tutorial-app
> npm install react-router-dom localforage match-sorter sort-by
> npm run dev
```

터미널에 인쇄된 URL을 방문할 수 있어야 합니다:

```
VITE v4.3.1  ready in 3083 ms

  ➜  Local:   http://localhost:5173/
  ➜  Network: use --host to expose
  ➜  press h to show help
```

> ✨ **주의사항**
>
> 윈도우의 정션링크로 연관된 경로에서 반드시 실제경로 문자만 인식하기 때문에, 실제 경로에 가서 실행해야한다.

이 튜토리얼을 위해 미리 작성된 CSS가 있으므로 React 라우터에 집중할 수 있습니다. 가혹하게 평가하거나 직접 작성해 보세요 😅 (이 튜토리얼의 마크업을 가능한 한 최소한으로 유지하기 위해 일반적으로 CSS에서 하지 않는 작업을 수행했습니다.)

**👉 [여기에 있는](https://gist.githubusercontent.com/ryanflorence/ba20d473ef59e1965543fa013ae4163f/raw/499707f25a5690d490c7b3d54c65c65eb895930c/react-router-6.4-tutorial-css.css) 튜토리얼 CSS를 src/index.css에 복사/붙여넣기합니다.**

이 튜토리얼에서는 데이터 생성, 읽기, 검색, 업데이트, 삭제에 대해 알아봅니다. 일반적인 웹 앱이라면 웹 서버의 API와 통신하겠지만, 여기서는 브라우저 저장소를 사용하고 네트워크 지연 시간을 속이려고 합니다. 이 코드 중 어느 것도 React Router와 관련이 없으므로 모두 복사/붙여넣기만 하면 됩니다.

**👉 [여기에 있는](https://gist.githubusercontent.com/ryanflorence/1e7f5d3344c0db4a8394292c157cd305/raw/f7ff21e9ae7ffd55bfaaaf320e09c6a08a8a6611/contacts.js) 튜토리얼 데이터 모듈을 src/contacts.js에 복사/붙여넣기합니다.**

src 폴더에 필요한 것은 contacts.js, main.jsx 및 index.css뿐입니다. 그 외의 모든 항목(예: App.js 및 에셋 등)은 삭제할 수 있습니다.

**👉 사용하지 않는 파일은 src/에서 삭제하여 이것만 남도록 합니다:**

```
 src
  ├── contacts.js
  ├── index.css
  └── main.jsx
```

앱이 실행 중이라면 잠시 멈출 수 있으니 계속 진행하세요 😋. 이제 시작할 준비가 되었습니다!



## 라우터 추가하기

가장 먼저 할 일은 [Broswer Router](https://reactrouter.com/en/main/routers/create-browser-router)를 생성하고 첫 번째 경로를 구성하는 것입니다. 이렇게 하면 웹 앱에 대한 클라이언트 측 라우팅이 활성화됩니다.

`main.jsx` 파일이 진입점입니다. 이 파일을 열면 페이지에 React Router를 넣을 것입니다.

**👉 `main.jsx`에서 [브라우저 라우터](https://reactrouter.com/en/main/routers/create-browser-router)를 생성하고 렌더링합니다.**

```jsx
import React from "react";
import ReactDOM from "react-dom/client";
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import "./index.css";

const router = createBrowserRouter([
  {
    path: "/",
    element: <div>Hello world!</div>,
  },
]);

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
```

이 첫 번째 경로는 나머지 경로가 그 안에서 렌더링되기 때문에 흔히 "root route"라고 부릅니다. 이 경로가 UI의 루트 레이아웃 역할을 하며, 더 멀리 갈수록 중첩된 레이아웃을 갖게 됩니다.



## Root Route

이 앱의 글로벌 레이아웃을 추가해 보겠습니다.

**👉 `src/routes` 및 `src/routes/root.jsx` 만들기**

```sh
mkdir src/routes
touch src/routes/root.jsx
```

(명령줄 덕후가 되고 싶지 않다면 해당 명령어 대신 편집기를 사용하세요 🤓).

**👉 루트 레이아웃 컴포넌트 만들기**

```jsx
export default function Root() {
  return (
    <>
      <div id="sidebar">
        <h1>React Router Contacts</h1>
        <div>
          <form id="search-form" role="search">
            <input
              id="q"
              aria-label="Search contacts"
              placeholder="Search"
              type="search"
              name="q"
            />
            <div
              id="search-spinner"
              aria-hidden
              hidden={true}
            />
            <div
              className="sr-only"
              aria-live="polite"
            ></div>
          </form>
          <form method="post">
            <button type="submit">New</button>
          </form>
        </div>
        <nav>
          <ul>
            <li>
              <a href={`/contacts/1`}>Your Name</a>
            </li>
            <li>
              <a href={`/contacts/2`}>Your Friend</a>
            </li>
          </ul>
        </nav>
      </div>
      <div id="detail"></div>
    </>
  );
}
```

아직 React 라우터와 관련된 내용은 없으므로 자유롭게 복사/붙여넣기 하세요.

**👉 root route의 `element`로 `<Root>`를 설정합니다.**

```jsx
/* existing imports */
import Root from "./routes/root";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
  },
]);

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
```

이제 앱이 다음과 같이 보일 것입니다. CSS도 작성할 수 있는 디자이너가 있으니 정말 좋죠? ([Jim](https://blog.jim-nielsen.com/)에게 고마워요 🙏).



## Not Found 오류 처리하기

새 앱을 만들 때 기능보다 버그가 훨씬 더 많이 발생하기 때문에 프로젝트 초기에 앱이 오류에 어떻게 대응하는지 파악하는 것이 좋습니다! 이런 오류가 발생했을 때 사용자에게 좋은 경험을 제공할 뿐만 아니라 개발 과정에서도 도움이 됩니다.

이 앱에 몇 가지 링크를 추가했는데, 링크를 클릭하면 어떻게 되는지 살펴볼까요?

**👉 사이드바 이름 중 하나를 클릭합니다.**

![image-20230421235049785](doc-resources/image-20230421235049785.png)

구려요! 이 앱의 루트 요소에 있는 flex box 스타일로 인해 더 심해진 React 라우터의 기본 오류 화면입니다 😂.

렌더링, 데이터 로드 또는 데이터 변이를 수행하는 동안 앱에서 오류가 발생할 때마다 React Router는 오류를 포착하고 오류 화면을 렌더링합니다. 나만의 오류 페이지를 만들어 봅시다.

**👉 오류 페이지 구성 요소 만들기**

```sh
touch src/error-page.jsx
```

* `src/error-page.jsx`

  ```jsx
  import { useRouteError } from "react-router-dom";
  
  export default function ErrorPage() {
    const error = useRouteError();
    console.error(error);
  
    return (
      <div id="error-page">
        <h1>Oops!</h1>
        <p>Sorry, an unexpected error has occurred.</p>
        <p>
          <i>{error.statusText || error.message}</i>
        </p>
      </div>
    );
  }
  ```

👉 root route에서 `<ErrorPage>`를 [`errorElement`](https://reactrouter.com/en/main/route/error-element)로 설정

* `src/main.jsx`

  ```jsx
  /* previous imports */
  import ErrorPage from "./error-page";
  
  const router = createBrowserRouter([
    {
      path: "/",
      element: <Root />,
      errorElement: <ErrorPage />,
    },
  ]);
  
  ReactDOM.createRoot(document.getElementById("root")).render(
    <React.StrictMode>
      <RouterProvider router={router} />
    </React.StrictMode>
  );
  ```

  오류 페이지는 이제 다음과 같아야 합니다.

  ![image-20230422000026514](doc-resources/image-20230422000026514.png)

(별로 나아지지 않았습니다. 누군가 디자이너에게 오류 페이지를 만들어 달라고 요청하는 것을 잊어버렸을 수도 있습니다. 디자이너에게 오류 페이지를 만들어 달라고 요청하는 것을 잊어버리고 디자이너가 생각하지 못했다고 디자이너를 탓할 수도 있겠죠 😆)

[`useRouteError`](https://reactrouter.com/en/main/hooks/use-route-error)는 발생한 오류를 제공합니다. 사용자가 존재하지 않는 경로로 이동하면 "Not Found" `statusText`가 포함된 [오류 응답](https://reactrouter.com/en/main/utils/is-route-error-response)을 받게 됩니다. 튜토리얼의 뒷부분에서 몇 가지 다른 오류를 살펴보고 이에 대해 자세히 설명하겠습니다.

지금은 무한 회전(infinite spinners), 응답하지 않는 페이지 또는 빈 화면 대신 이 페이지에서 거의 모든 오류를 처리할 수 있다는 것만으로도 충분합니다 🙌.



## 연락처 Route UI

404 "Not Found" 페이지 대신 링크한 URL에 실제로 무언가를 렌더링하고 싶습니다. 그러기 위해서는 새로운 경로를 만들어야 합니다.

**👉 연락처 route 모듈 생성**

```sh
touch src/routes/contact.jsx
```

👉 **연락처 컴포넌트 UI 추가**

여러 요소로 구성되어 있으므로 자유롭게 복사/붙여넣기할 수 있습니다.

* `src/route/contact.jsx`

  ```jsx
  import { Form } from "react-router-dom";
  
  export default function Contact() {
    const contact = {
      first: "Your",
      last: "Name",
      avatar: "https://placekitten.com/g/200/200",
      twitter: "your_handle",
      notes: "Some notes",
      favorite: true,
    };
  
    return (
      <div id="contact">
        <div>
          <img
            key={contact.avatar}
            src={contact.avatar || null}
          />
        </div>
  
        <div>
          <h1>
            {contact.first || contact.last ? (
              <>
                {contact.first} {contact.last}
              </>
            ) : (
              <i>No Name</i>
            )}{" "}
            <Favorite contact={contact} />
          </h1>
  
          {contact.twitter && (
            <p>
              <a
                target="_blank"
                href={`https://twitter.com/${contact.twitter}`}
              >
                {contact.twitter}
              </a>
            </p>
          )}
  
          {contact.notes && <p>{contact.notes}</p>}
  
          <div>
            <Form action="edit">
              <button type="submit">Edit</button>
            </Form>
            <Form
              method="post"
              action="destroy"
              onSubmit={(event) => {
                if (
                  !confirm(
                    "Please confirm you want to delete this record."
                  )
                ) {
                  event.preventDefault();
                }
              }}
            >
              <button type="submit">Delete</button>
            </Form>
          </div>
        </div>
      </div>
    );
  }
  
  function Favorite({ contact }) {
    // yes, this is a `let` for later
    let favorite = contact.favorite;
    return (
      <Form method="post">
        <button
          name="favorite"
          value={favorite ? "false" : "true"}
          aria-label={
            favorite
              ? "Remove from favorites"
              : "Add to favorites"
          }
        >
          {favorite ? "★" : "☆"}
        </button>
      </Form>
    );
  }
  ```

  이제 컴포넌트가 생겼으니 새 경로에 연결해 보겠습니다.

👉 연락처 컴포넌트 가져오기 및 새 경로 만들기

* `src/main.jsx`

  ```jsx
  /* existing imports */
  import Contact from "./routes/contact";
  
  const router = createBrowserRouter([
    {
      path: "/",
      element: <Root />,
      errorElement: <ErrorPage />,
    },
    {
      path: "contacts/:contactId",
      element: <Contact />,
    },
  ]);
  
  /* existing code */
  ```

이제 링크 중 하나를 클릭하거나 `/contacts/1`을 방문하면 새 구성 요소가 표시됩니다!

![image-20230422001755502](doc-resources/image-20230422001755502.png)

하지만 루트 레이아웃 내부에 있지 않습니다 😠.



## 중첩 라우트

우리는 연락처 컴포넌트가 다음과 같이 `<Root>` 레이아웃 내부에서 렌더링되기를 원합니다.

![image-20230422003446579](doc-resources/image-20230422003446579.png)

연락처 경로를 루트 경로의 *하위 (child)* 경로로 만들면 됩니다.

👉 연락처 경로를 루트 경로의 하위 경로로 이동합니다.

* `src/main.jsx`

  ```jsx
  const router = createBrowserRouter([
    {
      path: "/",
      element: <Root />,
      errorElement: <ErrorPage />,
      children: [
        {
          path: "contacts/:contactId",
          element: <Contact />,
        },
      ],
    },
  ]);
  ```

이제 루트 레이아웃이 다시 표시되지만 오른쪽에 여전히 빈 페이지가 표시됩니다. 루트 경로에 하위 경로를 렌더링할 위치를 알려줘야 합니다. [`<Outlet>`](https://reactrouter.com/en/main/components/outlet)으로 이를 수행합니다.

`<div id="detail">`를 찾아 outlet 안에 넣습니다.

**👉 [`<Outlet>`](https://reactrouter.com/en/main/components/outlet) 렌더링하기**

* `src/routes/root.jsx`

  ```jsx
  import { Outlet } from "react-router-dom";
  
  export default function Root() {
    return (
      <>
        {/* all the other elements */}
        <div id="detail">
          <Outlet />
        </div>
      </>
    );
  }
  ```

  

## 클라이언트 측 라우팅

눈치채셨을 수도 있고 그렇지 않으셨을 수도 있지만, 사이드바의 링크를 클릭하면 브라우저는 React 라우터를 사용하는 대신 다음 URL에 대한 전체 문서 요청을 수행합니다.

클라이언트 측 라우팅을 사용하면 앱이 서버에 다른 문서를 요청하지 않고도 URL을 업데이트할 수 있습니다. 대신 앱은 즉시 새 UI를 렌더링할 수 있습니다. [`<Link>`](https://reactrouter.com/en/main/components/link)를 통해 이를 구현해 보겠습니다.

**👉 사이드바의 `<a href>`를 `<Link>`로 변경합니다.**

* `src/routes/root.jsx`

  ```jsx
  import { Outlet, Link } from "react-router-dom";
  
  export default function Root() {
    return (
      <>
        <div id="sidebar">
          {/* other elements */}
  
          <nav>
            <ul>
              <li>
                <Link to={`contacts/1`}>Your Name</Link>
              </li>
              <li>
                <Link to={`contacts/2`}>Your Friend</Link>
              </li>
            </ul>
          </nav>
  
          {/* other elements */}
        </div>
      </>
    );
  }
  ```

  브라우저 개발자 도구에서 네트워크 탭을 열어 더 이상 문서를 요청하지 않는지 확인할 수 있습니다.



## 데이터 로드

URL 세그먼트, 레이아웃, 데이터는 종종 함께 결합되어(세 배로?) 있습니다. 이 앱에서 이미 확인할 수 있습니다:

| URL 세그먼트 | 컴포넌트    | 데이터      |
| ------------ | ----------- | ----------- |
| /            | `<Root>`    | 연락처 목록 |
| contacts/:id | `<Contact>` | 개별 연락처 |

이러한 자연스러운 결합으로 인해 React 라우터에는 경로 구성 요소에 데이터를 쉽게 가져올 수 있는 데이터 규칙이 있습니다.

데이터를 로드하는 데 사용할 두 가지 API는 [`loader`](https://reactrouter.com/en/main/route/loader)와 [`useLoaderData`](https://reactrouter.com/en/main/hooks/use-loader-data)입니다. 먼저 Root 모듈에서 loader 함수를 생성하고 내보낸 다음 route에 연결합니다. 마지막으로 데이터에 액세스하고 렌더링합니다.

👉 root.jsx에서 loader 내보내기

* `src/routes/root.jsx`

  ```jsx
  import { Outlet, Link } from "react-router-dom";
  import { getContacts } from "../contacts";
  
  export async function loader() {
    const contacts = await getContacts();
    return { contacts };
  }
  ```

**👉 route에서 loader 구성하기**

* `src/main.jsx`

  ```jsx
  /* other imports */
  import Root, { loader as rootLoader } from "./routes/root";
  
  const router = createBrowserRouter([
    {
      path: "/",
      element: <Root />,
      errorElement: <ErrorPage />,
      loader: rootLoader,
      children: [
        {
          path: "contacts/:contactId",
          element: <Contact />,
        },
      ],
    },
  ]);
  ```

  

**👉 데이터 엑세스 및 렌더링**

* `src/routes/root.jsx`

  ```jsx
  import {
    Outlet,
    Link,
    useLoaderData,
  } from "react-router-dom";
  import { getContacts } from "../contacts";
  
  /* other code */
  
  export default function Root() {
    const { contacts } = useLoaderData();
    return (
      <>
        <div id="sidebar">
          <h1>React Router Contacts</h1>
          {/* other code */}
  
          <nav>
            {contacts.length ? (
              <ul>
                {contacts.map((contact) => (
                  <li key={contact.id}>
                    <Link to={`contacts/${contact.id}`}>
                      {contact.first || contact.last ? (
                        <>
                          {contact.first} {contact.last}
                        </>
                      ) : (
                        <i>No Name</i>
                      )}{" "}
                      {contact.favorite && <span>★</span>}
                    </Link>
                  </li>
                ))}
              </ul>
            ) : (
              <p>
                <i>No contacts</i>
              </p>
            )}
          </nav>
  
          {/* other code */}
        </div>
      </>
    );
  }
  ```

  이제 끝입니다! 이제 React 라우터가 자동으로 해당 데이터를 UI와 동기화합니다. 아직 데이터가 없으므로 이와 같은 빈 목록이 표시될 것입니다:

![image-20230422010643260](doc-resources/image-20230422010643260.png)



## 데이터 쓰기 + HTML 폼

첫 번째 접점은 잠시 후에 만들겠지만, 먼저 HTML에 대해 이야기해 보겠습니다.

React 라우터는 자바스크립트 캄브리아기 폭발 이전의 웹 개발에 따라 데이터 변이 프리미티브로서 HTML 양식 탐색을 에뮬레이트합니다. "구식" 웹 모델의 단순함과 함께 클라이언트 렌더링 앱의 UX 기능을 제공합니다.

일부 웹 개발자에게는 생소할 수 있지만 HTML 양식은 실제로 링크를 클릭하는 것과 마찬가지로 브라우저에서 탐색을 유발합니다. 유일한 차이점은 요청에 있습니다. 링크는 URL만 변경할 수 있지만 양식은 요청 방법(GET 대 POST)과 요청 본문(POST 양식 데이터)도 변경할 수 있습니다.

클라이언트 측 라우팅이 없으면 브라우저는 양식의 데이터를 자동으로 직렬화하여 POST의 경우 요청 본문으로, GET의 경우 URLSearchParams로 서버에 전송합니다. React 라우터도 동일한 작업을 수행하지만 요청을 서버로 보내는 대신 클라이언트 측 라우팅을 사용하여 [`action`](https://reactrouter.com/en/main/route/action) 경로로 전송합니다.

앱에서 "새로 만들기" 버튼을 클릭하여 이를 테스트해 볼 수 있습니다. Vite 서버가 POST 요청을 처리하도록 구성되지 않았기 때문에 앱이 폭파되어야 합니다(404를 보내지만 405 가 되어야 함 🤷).

![image-20230422124958811](doc-resources/image-20230422124958811.png)

새 연락처를 생성하기 위해 해당 POST를 Vite 서버로 보내는 대신 클라이언트 측 라우팅을 사용해 보겠습니다.