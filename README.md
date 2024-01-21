# API 명세서

### MEMBER

<table>
  <td>Method</td>
  <td>URL</td>
  <td>Request</td>
  <td>Response</td>
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
    <td>회원가입 응답(미작성)</td>
    <td>회원가입</td>
  </tr>

  <tr>
    <td>POST</td>
    <td>/members/signin</td>
<td>

```json
{
  "loginId": "LSM",
  "loginPwd": "1234"
}
```

</td>
<td>

```json
{
  "status": "success",
  "data": {
    "expire_in": 1500,
    "token": "tokenString"
  },
  "message": "성공적으로 로그인하였습니다."
}
```

</td>
    <td>로그인</td>
  </tr>

</table>

### POSTER

<table>
  <td>Method</td>
  <td>URL</td>
  <td>Request</td>
  <td>Response</td>
  <td>Description</td>
  <tr>
    <td>POST</td>
    <td>/locations/{locationId}/posters</td>
  <td>

```json

```

  </td>
<td>

```json
{
  "status": "success",
  "data": {
    "id": 2,
    "writer": {
      "id": 12,
      "name": "TESTNAME",
      "role": "USER",
      "regDate": "2024-01-20T16:11:55",
      "type": "NORMAL"
    },
    "location": {
      "id": 4,
      "latitude": 35.24154,
      "longitude": 128.6957,
      "title": "컴공 건물",
      "address": "공대5호관",
      "description": "끄아아악"
    },
    "title": "title2",
    "content": "content2",
    "regDate": "2024-01-21T14:29:40.905984"
  },
  "message": "게시글 작성 성공."
}
```

</td>
    <td>게시글 작성</td>
  </tr>

  <tr>
    <td>GET</td>
    <td>/locations/{loationId}/posters</td>
<td>

```json
{"미작성" : "미작성"}
```

</td>
<td>

```json
{
  "status": "success",
  "data": [
    {
      "id": 1,
      "writer": {
        "id": 12,
        "name": "TESTNAME",
        "role": "USER",
        "regDate": "2024-01-20T16:11:55",
        "type": "NORMAL"
      },
      "title": "title",
      "content": "content",
      "regDate": "2024-01-21T14:25:09"
    },
    {
      "id": 2,
      "writer": {
        "id": 12,
        "name": "TESTNAME",
        "role": "USER",
        "regDate": "2024-01-20T16:11:55",
        "type": "NORMAL"
      },
      "title": "title2",
      "content": "content2",
      "regDate": "2024-01-21T14:29:41"
    }
  ],
  "message": "전체 게시글 조회 성공."
}
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
  "status": "success",
  "data": {
    "id": 2,
    "writer": {
      "id": 12,
      "name": "TESTNAME",
      "role": "USER",
      "regDate": "2024-01-20T16:11:55",
      "type": "NORMAL"
    },
    "title": "title2",
    "content": "content2",
    "regDate": "2024-01-21T14:29:41"
  },
  "message": "특정 게시글 조회 성공."
}
```

</td>
    <td>특정 게시글 조회</td>
  </tr>

</table>


### COMMENT

### IMAGEFILE
