


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
    <td>/members</td>
  <td>

```json
{
    "name" : "닉네임",
    "loginId" : "아이디",
    "loginPwd" : "비밀번호"
}
```

  </td>
    <td>회원가입 응답(미작성)</td>
    <td>회원가입</td>
  </tr>
  
  <tr>
    <td>POST</td>
    <td>/login</td>
<td>

```json
{
    "loginId" : "LSM",
    "loginPwd" : "1234"
}
```

</td>
    <td>로그인 응답(미작성)</td>
    <td>로그인</td>
  </tr>

</table>
