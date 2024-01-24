# API 명세서
<hr>

### API 요청 헤더

유저 정보가 필요 없는, 서버에서 인증 과정을 필요로하지 않는 요청은 Authorization 헤더가 필요 없지만<br>
POST, PUT, DELETE와 같은 인증이 필요한 요청은 Authorization 헤더를 필요로 합니다.

<br>

<table>
    <thead>
        <th>요청 헤더명</th>
        <th>설명</th>
    </thead>
    <tbody>
        <tr>
            <td>Authorization</td>
            <td>인증을 필요로하는 요청을 하기 위해 접근 토큰(access_token)을 전달하는 헤더. <br> Authorization : {토큰 타입} {토큰 값}</td>
        </tr>
    </tbody>
</table>

#### 요청 헤더 예
Authorization : Bearer AaA.bBb.CcC


<hr>


### 에러 메시지 형식

에러 메시지의 형식은 JSON이며 다음과 같이 코드와 메시지를 갖는다.

```json
{
  "errorCode": "NOT_VALID",
  "message": "아이디를 비울 수 없습니다."
}
```

해당 에러 코드에 대한 상세한 내용은 message로 제공되며, 응답의 HTTP 상태 코드 또한 포함됩니다.

<br>

#### 공통 에러 코드

<table>
    <thead>
        <tr>
            <th>HTTP 상태 코드</th>
            <th>에러 코드</th>
            <th>설명</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>400 Bad Request</td>
            <td>"NOT_VALID"</td>
            <td>API 요청시 필요한 필수 정보가 없습니다.</td>
        </tr>
        <tr>
            <td>401 Unauthorized</td>
            <td>"UNAUTHORIZED_CLIENT"</td>
            <td>토큰 정보가 없습니다.</td>
        </tr>
        <tr>
            <td>401 Unauthorized</td>
            <td>"EXPIRED_TOKEN"</td>
            <td>만료된 토큰입니다.</td>
        </tr>
        <tr>
            <td>401 Unauthorized</td>
            <td>"JWT_DECODE_FAIL"</td>
            <td>토큰 정보가 올바르지 않습니다.</td>
        </tr>
        <tr>
            <td>401 Unauthorized</td>
            <td>"JWT_SIGNATURE_FAIL"</td>
            <td>토큰 정보가 올바르지 않습니다.</td>
        </tr>
        <tr>
            <td>403 Forbidden</td>
            <td>"FORBIDDEN_CLIENT"</td>
            <td>접근 권한이 없습니다.</td>
        </tr>
    </tbody>
</table>


<hr>


### MEMBER

<table>
  <td>Method</td>
  <td>URL</td>
  <td>Request Body</td>
  <td>Response Body</td>
  <td>Description</td>
  <tr>
    <td>POST</td>
    <td>/members/signup</td>
  <td>

```json
{
  "name": "닉네임",
  "loginId": "아이디",
  "loginPwd": "비밀번호"
}
```

  </td>
<td>

```json
{
  "id": 4,
  "name": "TESTNAME",
  "role": "USER"
}
```

</td>
    <td>회원가입</td>
  </tr>

  <tr>
    <td>POST</td>
    <td>/members/signin</td>
<td>

```json
{
  "loginId": "아이디",
  "loginPwd": "비밀번호"
}
```

</td>
<td>

```json
{
  "expire_in": 1500,
  "token": "tokenString"
}
```

</td>
    <td>로그인</td>
  </tr>

</table>




#### - 로그인

##### 응답

<table>
    <thead>
        <tr>
            <th>이름</th>
            <th>타입</th>
            <th>설명</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>expire_in</td>
            <td>Long</td>
            <td>토큰 만료 시간을 나타내며 단위는 초이다.</td>
        </tr>
        <tr>
            <td>token</td>
            <td>String</td>
            <td>사용자 토큰 값</td>
        </tr>
    </tbody>
</table>

<br>

#### 에러 코드

<table>
    <thead>
        <tr>
            <th>HTTP 상태 코드</th>
            <th>에러 코드</th>
            <th>에러 메시지</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>400</td>
            <td>"NOT_VALID"</td>
            <td>아이디, 비밀번호 또는 닉네임을 입력하지 않음</td>
        </tr>
        <tr>
            <td>401</td>
            <td>"FAIL_LOGIN"</td>
            <td>잘못된 아이디, 비밀번호를 입력 했음</td>
        </tr>
        <tr>
            <td rowspan="2">409</td>
            <td>"DUPLICATE_LOGINID"</td>
            <td rowspan="2">이미 다른 유저가 사용중인 닉네임 또는 아이디를 등록</td>
        </tr>
        <tr>
            <td>"DUPLICATE_NAME"</td>
        </tr>
    </tbody>
</table>


<hr>


### LOCATION
<table>
    <thead>
        <tr>
            <th>Method</th>
            <th>URL</th>
            <th>Request</th>
            <th>Response</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>GET</td>
            <td>/locations?latitude={latitude값}&longitude={longitude값}</td>
            <td></td>
<td>

```json

[
  {
    "id": 1,
    "latitude": 11.24308,
    "longitude": 11.6934,
    "title": "AAA",
    "address": "BBB",
    "description": "낭만 가득 운동장"
  },
  {
    "id": 3,
    "latitude": 11.24666,
    "longitude": 11.6909,
    "title": "CCC",
    "address": "DDD",
    "description": "공부하자 아냐 그건 너무 교과서야"
  }
]

```

</td>
            <td>위도, 경도로 주위 장소 조회</td>
        </tr>
        <tr>
            <td>POST</td>
            <td>/locations</td>
<td>

Content-Type : multipart/form-data

이름 : locationRequest <br>
설명 : 장소 정보

```json
{
  "latitude": 1.1,
  "longitude": 2.2,
  "title": "테스트장소",
  "address": "주소",
  "description": "설명"
}
```

<br>
이름 : files <br>
설명 : 첨부 이미지 파일 <br>
필수 : X

</td>
<td>

```json
{
  "id": 13,
  "title": "title4",
  "content": "content4",
  "regDate": "2024-01-24T15:13:17.118866"
}
```

</td>
            <td>장소 등록</td>
        </tr>
        <tr>
            <td>GET</td>
            <td>/locations/{locationId}</td>
            <td></td>
<td>

```json
{
  "id": 1,
  "latitude": 1.1,
  "longitude": 2.2,
  "title": "장소명",
  "address": "주소",
  "description": "부가 설명"
}
```

</td>
            <td>장소ID로 조회</td>
        </tr>
    </tbody>
</table>


<br>
<hr>
<br>


### POSTER

<table>
  <td>Method</td>
  <td>URL</td>
  <td>Request Body</td>
  <td>Response</td>
  <td>Description</td>
  <tr>
    <td>POST</td>
    <td>/locations/{locationId}/posters</td>
  <td>

Content-Type : multipart/form-data

이름 : posterRequest <br>
설명 : 게시글 내용

```json
{
  "title":"HAHA",
  "content":"HOHO"
}
```

<br>
이름 : files <br>
설명 : 첨부 이미지 파일 <br>
필수 : X

  </td>
<td>

```json
{
  "id": 9,
  "title": "title3",
  "content": "content3",
  "regDate": "2024-01-24T14:20:48.901509"
}
```

</td>
    <td>게시글 작성</td>
  </tr>

  <tr>
    <td>GET</td>
    <td>/locations/{loationId}/posters</td>
<td>
</td>
<td>

```json
[
  {
    "id": 10,
    "title": "title3",
    "content": "content3",
    "regDate": "2024-01-24T14:24:17"
  },
  {
    "id": 11,
    "title": "title4",
    "content": "content4",
    "regDate": "2024-01-24T14:25:40"
  }
]
```

</td>
    <td>전체 게시글 조회</td>
  </tr>

<tr>
    <td>GET</td>
    <td>/posters/{posterId}</td>
<td>
</td>
<td>

```json
{
  "id": 10,
  "title": "title3",
  "content": "content3",
  "regDate": "2024-01-24T14:24:17"
}
```

</td>
    <td>특정 게시글 조회</td>
  </tr>

</table>


<hr>


### COMMENT

<table>
    <thead>
        <tr>
            <th>Method</th>
            <th>URL</th>
            <th>Request</th>
            <th>Response</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
    </tbody>
</table>


<br>
<hr>
<br>


### IMAGEFILE

<table>
    <thead>
        <tr>
            <th>Method</th>
            <th>URL</th>
            <th>Request</th>
            <th>Response</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>GET</td>
            <td>/posters/{posterId}/images</td>
            <td></td>
<td>

```json
[
  {
    "id": 11,
    "uploadFileName": "3.png"
  },
  {
    "id": 12,
    "uploadFileName": "2.png"
  }
]
```

</td>
            <td>특정 게시글의 첨부 이미지 목록 조회</td>
        </tr>
        <tr>
            <td>GET</td>
            <td>/posters/images/{posterImageId}</td>
            <td></td>
            <td>(body에 이미지파일. 아니면 파일 자체 링크로도 좋을듯)</td>
            <td>특정 이미지 조회</td>
        </tr>
        <tr>
            <td>GET</td>
            <td>/locations/{locationId}/images</td>
            <td></td>
<td>

```json
[
  {
    "id": 1,
    "uploadFileName": "1.png"
  },
  {
    "id": 2,
    "uploadFileName": "zzhaha.png"
  }
]
```

</td>
            <td>특정 장소의 첨부 이미지 목록 조회</td>
        </tr>
        <tr>
            <td>GET</td>
            <td>/locations/images/{locationImageId}</td>
            <td></td>
            <td>(body에 이미지파일. 아니면 파일 자체 링크도 좋을듯)</td>
            <td>특정 이미지 조회</td>
        </tr>
    </tbody>
</table>
