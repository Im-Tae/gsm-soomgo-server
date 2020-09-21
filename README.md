## GSM-SoomGo-Server



### use MongoDB

```
mongo localhost:27017
```

```
> use jobtang
```

```
> show dbs // 사용중인 db Collections 보기
```

```
> db.User.find() // 유저 데이터 보기
> db.Board.find() // 게시판 데이터 보기
```



```
> db.User.createOne ({"name": "test1@gmail.com"}) // 데이터 삽입
```

```
> db.User.findOne ({"name": "test1@gmail.com"}) // 데이터 조회
```

```
> db.User.updateOne ({"name": "test1@gmail.com"}, {"name": "test2@gmail.com"}) // 데이터 수정
```

```
> db.User.remove ({"name": "test1@gmail.com"}) // 데이터 삭제
```



</br></br>

### use API

[Notion Link](https://www.notion.so/API-e0ffe45ff8fd4580adafd9905536bddd)



</br></br>

### contribution

[![](https://sourcerer.io/fame/Im-Tae/Im-Tae/gsm-soomgo-server/images/0)](https://sourcerer.io/fame/Im-Tae/Im-Tae/gsm-soomgo-server/links/0)[![](https://sourcerer.io/fame/Im-Tae/Im-Tae/gsm-soomgo-server/images/1)](https://sourcerer.io/fame/Im-Tae/Im-Tae/gsm-soomgo-server/links/1)[![](https://sourcerer.io/fame/Im-Tae/Im-Tae/gsm-soomgo-server/images/2)](https://sourcerer.io/fame/Im-Tae/Im-Tae/gsm-soomgo-server/links/2)[![](https://sourcerer.io/fame/Im-Tae/Im-Tae/gsm-soomgo-server/images/3)](https://sourcerer.io/fame/Im-Tae/Im-Tae/gsm-soomgo-server/links/3)[![](https://sourcerer.io/fame/Im-Tae/Im-Tae/gsm-soomgo-server/images/4)](https://sourcerer.io/fame/Im-Tae/Im-Tae/gsm-soomgo-server/links/4)[![](https://sourcerer.io/fame/Im-Tae/Im-Tae/gsm-soomgo-server/images/5)](https://sourcerer.io/fame/Im-Tae/Im-Tae/gsm-soomgo-server/links/5)[![](https://sourcerer.io/fame/Im-Tae/Im-Tae/gsm-soomgo-server/images/6)](https://sourcerer.io/fame/Im-Tae/Im-Tae/gsm-soomgo-server/links/6)[![](https://sourcerer.io/fame/Im-Tae/Im-Tae/gsm-soomgo-server/images/7)](https://sourcerer.io/fame/Im-Tae/Im-Tae/gsm-soomgo-server/links/7)



</br></br>

### License

```
Copyright 2020 Im-Tae (TaeGeon Lim)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

