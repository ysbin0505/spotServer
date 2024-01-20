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
