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

### LOCATION

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
    "id": 3,
    "writer": {
      "id": 12,
      "name": "TESTNAME",
      "role": "USER",
      "regDate": "2024-01-20T16:11:55",
      "type": "NORMAL"
    },
    "title": "title3",
    "content": "content3",
    "regDate": "2024-01-21T14:50:43.340146"
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
{
    "status": "success",
    "data": [
        {
            "id": 1,
            "uploadFileName": "3.png",
            "storeFileName": "993d33cc-c8e3-4dd2-9be7-4186e7110878.png"
        }
    ],
    "message": "요청 처리 완료."
}
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
{
    "status": "success",
    "data": [
        {
            "id": 8,
            "uploadFileName": "3.png",
            "storeFileName": "993d33cc-c8e3-4dd2-9be78.png"
        }
    ],
    "message": "요청 처리 완료."
}
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
