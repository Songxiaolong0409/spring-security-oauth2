# Identity Service （IDS） 接口文档模版

	
项目|	作者	|日期	|版本号
----|-------|------|-------
接口文档模版|	宋晓龙|	2020-03-30	|v1.0

## 说明

对接前请向服务端申请IDS系统ip、client_id、client_secret等参数。

## 1. 统一接口返回

1.1 接口错误返回格式 
    
    {
        "success": false,
        "code": 500,
        "message": "Send SMS Code Error: AuthorizedGrantTypes is not found."
    } 

1.2 接口正确返回格式

    {
        "success": true,
        "code": 200,
        "message": "操作成功"
        "data":""
    }
    
data 字段中为返回的具体数据，具体接口内容会再说明。
以下接口只解释data字段中的返回。

##2. 获取短信验证码

2.1. 功能说明 

	登录前获取短信验证码
	
2.2. 请求地址

	/user/getSmsCode

2.3. 请求方式

	http-post application/json
	
2.4. 请求参数说明	

参数名|说明 |类型 |是否必填
----|-------|------|-------
client_id|客户端id|String|y
client_secret|客户度密钥|String |Y
grant_type|获取token方式,本例中固定值'sms'|String|y
	
2.5. 请求参数示例

    {
        "client_id":"driver_ios_911427",
        "client_secret":"Fih320357",
        "grant_type":"sms"
    }	

2.6. 接口返回示例

		{
            "success": true,
            "code": 200,
            "msg": "操作成功"
        }
        
### 3. 短信形式获取 access_token

3.1. 请求地址

	/oauth/token

3.2. 请求方式

	http-post Params

3.3 请求参数说明

参数名|说明 |类型 |是否必填
----|-------|------|-------
client_id|客户端id|	String|Y
client_secret|客户度密钥|String |Y
grant_type|获取token方式,本例中固定值'sms'|String|y
area_code|手机区号。例如"+86"，"%2B86"|String|y
mobile|手机号|String|Y
smscode|短信验证码|String|Y

3.4 返回参数说明

参数名|说明 |类型 |是否必填
----|-------|------|-------
user_id |用户id|int|y
user_name|用户对象，包含clientId、areaCode、mobile|	String|Y
jti|token id|String |Y
accessToken|请求后续接口需要的token|String|Y
refreshToken|刷新accessToken时需要的token|String|Y

3.5. 接口返回示例

    {
        "success": true,
        "code": 200,
        "message": "操作成功",
        "data": {
            "user_id": 1,
            "user_name": {
                "clientId": "driver_ios_911427",
                "areaCode": "+86",
                "mobile": "13418884600"
            },
            "jti": "0a7f06a8-49de-4b71-83c9-09b9592acb27",
            "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODYwODc4MjksInVzZXJfaWQiOjEsInVzZXJfbmFtZSI6eyJjbGllbnRJZCI6ImRyaXZlcl9pb3NfOTExNDI3IiwiYXJlYUNvZGUiOiIrODYiLCJtb2JpbGUiOiIxMzQxODg4NDYwMCJ9LCJqdGkiOiIwYTdmMDZhOC00OWRlLTRiNzEtODNjOS0wOWI5NTkyYWNiMjciLCJjbGllbnRfaWQiOiJkcml2ZXJfaW9zXzkxMTQyNyIsInNjb3BlIjpbImFsbCJdfQ.oFtR9F6jLRycsekYMTyehvbrNkkPZG4KmBJzIO59nDA",
            "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJ1c2VyX25hbWUiOnsiY2xpZW50SWQiOiJkcml2ZXJfaW9zXzkxMTQyNyIsImFyZWFDb2RlIjoiKzg2IiwibW9iaWxlIjoiMTM0MTg4ODQ2MDAifSwic2NvcGUiOlsiYWxsIl0sImF0aSI6IjBhN2YwNmE4LTQ5ZGUtNGI3MS04M2M5LTA5Yjk1OTJhY2IyNyIsImV4cCI6MTU4NjYwNjIyOSwianRpIjoiYTZjZmI0YjItZTFkMy00MWIwLTk1NTItYmE2Mzc1ZWI3YjRjIiwiY2xpZW50X2lkIjoiZHJpdmVyX2lvc185MTE0MjcifQ.fyNynFA2OTwIn5yr2ahN_RwifVFoCGUC7RMbGSBuaDA"
        }
    }
    
### 4. 手机号密码方式获取 access_token

4.1. 请求地址

	/oauth/token

4.2. 请求方式

	http-post Params

4.3 请求参数说明

参数名|说明 |类型 |是否必填
----|-------|------|-------
client_id|客户端id|	String|Y
client_secret|客户度密钥|String |Y
grant_type|获取token方式,本例中固定值'pwd'|String|y
area_code|手机区号。例如"+86"，"%2B86"|String|y
mobile|手机号|String|Y
password|密码|String|Y

4.4 返回参数说明

参数名|说明 |类型 |是否必填
----|-------|------|-------
user_id |用户id|int|y
user_name|用户对象，包含clientId、areaCode、mobile|	String|Y
jti|token id|String |Y
accessToken|请求后续接口需要的token|String|Y
refreshToken|刷新accessToken时需要的token|String|Y

4.5. 接口返回示例

    {
        "success": true,
        "code": 200,
        "message": "操作成功",
        "data": {
            "user_id": 1,
            "user_name": {
                "clientId": "driver_ios_911427",
                "areaCode": "+86",
                "mobile": "13418884600"
            },
            "jti": "b8fc030e-bc9a-41da-8629-afe4039493b6",
            "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODYwODgzNTUsInVzZXJfaWQiOjEsInVzZXJfbmFtZSI6eyJjbGllbnRJZCI6ImRyaXZlcl9pb3NfOTExNDI3IiwiYXJlYUNvZGUiOiIrODYiLCJtb2JpbGUiOiIxMzQxODg4NDYwMCJ9LCJqdGkiOiJiOGZjMDMwZS1iYzlhLTQxZGEtODYyOS1hZmU0MDM5NDkzYjYiLCJjbGllbnRfaWQiOiJkcml2ZXJfaW9zXzkxMTQyNyIsInNjb3BlIjpbImFsbCJdfQ.xzIRyWnFoWFHnai6rmI1nUtUrvizOExGOWSDZRTA4pA",
            "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJ1c2VyX25hbWUiOnsiY2xpZW50SWQiOiJkcml2ZXJfaW9zXzkxMTQyNyIsImFyZWFDb2RlIjoiKzg2IiwibW9iaWxlIjoiMTM0MTg4ODQ2MDAifSwic2NvcGUiOlsiYWxsIl0sImF0aSI6ImI4ZmMwMzBlLWJjOWEtNDFkYS04NjI5LWFmZTQwMzk0OTNiNiIsImV4cCI6MTU4NjYwNjc1NSwianRpIjoiYjkyOGNhNDctMmJhMC00Njk5LThjMjQtZDI4MzM2YmEyNWIwIiwiY2xpZW50X2lkIjoiZHJpdmVyX2lvc185MTE0MjcifQ.VP_pmUQMnvrwKzX4l3ig9uyqEMjb347MvY-uEKzXe14"
        }
    }
    
### 5. 用户密码方式获取 access_token
     
5.1. 请求地址
     
    /oauth/token
     
5.2. 请求方式
     
    http-post Params
     
5.3 请求参数说明
     
参数名|说明 |类型 |是否必填
----|-------|------|-------
client_id|客户端id|	String|Y
client_secret|客户度密钥|String |Y
grant_type|获取token方式,本例中固定值'password'|String|y
username|用户名|String|Y
password|密码|String|Y
     
5.4 返回参数说明
     
参数名|说明 |类型 |是否必填
----|-------|------|-------
user_id |用户id|int|y
user_name|用户对象，包含clientId、areaCode、mobile|	String|Y
jti|token id|String |Y
accessToken|请求后续接口需要的token|String|Y
refreshToken|刷新accessToken时需要的token|String|Y
     
5.5. 接口返回示例
     
     {
        "success": true,
        "code": 200,
        "message": "操作成功",
        "data": {
            "user_id": 1,
            "user_name": {
                "clientId": "driver_ios_911427",
                 "areaCode": "+86",
                 "mobile": "13418884600"
            },
            "jti": "b8fc030e-bc9a-41da-8629-afe4039493b6",
            "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODYwODgzNTUsInVzZXJfaWQiOjEsInVzZXJfbmFtZSI6eyJjbGllbnRJZCI6ImRyaXZlcl9pb3NfOTExNDI3IiwiYXJlYUNvZGUiOiIrODYiLCJtb2JpbGUiOiIxMzQxODg4NDYwMCJ9LCJqdGkiOiJiOGZjMDMwZS1iYzlhLTQxZGEtODYyOS1hZmU0MDM5NDkzYjYiLCJjbGllbnRfaWQiOiJkcml2ZXJfaW9zXzkxMTQyNyIsInNjb3BlIjpbImFsbCJdfQ.xzIRyWnFoWFHnai6rmI1nUtUrvizOExGOWSDZRTA4pA",
            "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJ1c2VyX25hbWUiOnsiY2xpZW50SWQiOiJkcml2ZXJfaW9zXzkxMTQyNyIsImFyZWFDb2RlIjoiKzg2IiwibW9iaWxlIjoiMTM0MTg4ODQ2MDAifSwic2NvcGUiOlsiYWxsIl0sImF0aSI6ImI4ZmMwMzBlLWJjOWEtNDFkYS04NjI5LWFmZTQwMzk0OTNiNiIsImV4cCI6MTU4NjYwNjc1NSwianRpIjoiYjkyOGNhNDctMmJhMC00Njk5LThjMjQtZDI4MzM2YmEyNWIwIiwiY2xpZW50X2lkIjoiZHJpdmVyX2lvc185MTE0MjcifQ.VP_pmUQMnvrwKzX4l3ig9uyqEMjb347MvY-uEKzXe14"
        }
     }
         
### 6. 验证 access_token

6.1. 请求地址

	/oauth/check_token

6.2. 请求方式

	http-post,http-get Params

6.3 请求参数说明

参数名|说明 |类型 |是否必填
----|-------|------|-------
token|access_token,不能使用refreshToken|	String|Y

6.4 返回参数说明

参数名|说明 |类型 |是否必填
----|-------|------|-------
user_id |用户id|int|y
user_name|用户对象，包含clientId、areaCode、mobile|	String|Y
scope|暂无使用|String[]|y
exp|token有效时间|String|y
jti|token id|String |y
client_id|客户端id|	String|y

6.5. 接口返回示例

    {
        "user_id": 1,
        "user_name": {
            "userId": 1,
            "clientId": "driver_ios_911427",
            "areaCode": "+86",
            "mobile": "13418884600",
            "username": "admin",
            "enabled": true,
            "credentialsNonExpired": true,
            "accountNonLocked": true,
            "accountNonExpired": true
        },
        "scope": [
            "all"
        ],
        "active": true,
        "exp": 1586167448,
        "jti": "fded55e4-0e47-4d3b-b7f3-a38ead8296a0",
        "client_id": "driver_ios_911427"
    }
    
### 7. 刷新 access_token

7.1. 请求地址

	/oauth/token

7.2. 请求方式

	http-post Params

7.3 请求参数说明

参数名|说明 |类型 |是否必填
----|-------|------|-------
client_id|客户端id|	String|Y
client_secret|客户度密钥|String |Y
grant_type|获取token方式,本例中固定值'refresh_token'|String|y
refresh_token|refreshToken|String|y

7.4 返回参数说明

参数名|说明 |类型 |是否必填
----|-------|------|-------
user_id |用户id|int|y
user_name|用户对象，包含clientId、areaCode、mobile|	String|Y
jti|token id|String |Y
accessToken|请求后续接口需要的token|String|Y
refreshToken|刷新accessToken时需要的token|String|Y

7.5. 接口返回示例

    {
        "success": true,
        "code": 200,
        "message": "操作成功",
        "data": {
            "user_id": null,
            "user_name": {
                "enabled": true,
                "credentialsNonExpired": true,
                "accountNonLocked": true,
                "accountNonExpired": true
            },
            "jti": "8a5aebb2-683b-4013-a3a4-031e285f4b57",
            "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODYxNjc5OTEsInVzZXJfaWQiOm51bGwsInVzZXJfbmFtZSI6eyJlbmFibGVkIjp0cnVlLCJjcmVkZW50aWFsc05vbkV4cGlyZWQiOnRydWUsImFjY291bnROb25Mb2NrZWQiOnRydWUsImFjY291bnROb25FeHBpcmVkIjp0cnVlfSwianRpIjoiOGE1YWViYjItNjgzYi00MDEzLWEzYTQtMDMxZTI4NWY0YjU3IiwiY2xpZW50X2lkIjoiZHJpdmVyX2lvc185MTE0MjciLCJzY29wZSI6WyJhbGwiXX0.VEt4t7IdUACccKnWmjgKDUpbJw1921q5GYVqRI5t9Yc",
            "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJ1c2VyX25hbWUiOnsidXNlcklkIjoxLCJjbGllbnRJZCI6ImRyaXZlcl9pb3NfOTExNDI3IiwiYXJlYUNvZGUiOiIrODYiLCJtb2JpbGUiOiIxMzQxODg4NDYwMCIsInVzZXJuYW1lIjoiYWRtaW4iLCJlbmFibGVkIjp0cnVlLCJjcmVkZW50aWFsc05vbkV4cGlyZWQiOnRydWUsImFjY291bnROb25Mb2NrZWQiOnRydWUsImFjY291bnROb25FeHBpcmVkIjp0cnVlfSwic2NvcGUiOlsiYWxsIl0sImF0aSI6ImZkZWQ1NWU0LTBlNDctNGQzYi1iN2YzLWEzOGVhZDgyOTZhMCIsImV4cCI6MTU4NjY4NTg0NywianRpIjoiYjQzYzMxZmItZmNiNy00MzI2LTg1OGYtNjVjYmY1MmYwM2E2IiwiY2xpZW50X2lkIjoiZHJpdmVyX2lvc185MTE0MjcifQ.K4O6M-7C2TYUa4UpnGwxhJxfuxqBlK8NxQq8PPE2atE"
        }
    }
    
### 8. 退出登录，注销 access_token

8.1. 请求地址

	/loginout

8.2. 请求方式

	http-post Authorization requet Headers Access Token

8.3 请求参数说明

参数名|说明 |类型 |是否必填
----|-------|------|-------
Access Token|需要注销的Access Token|	String|Y

8.4. 接口返回示例

    {
        "success": true,
        "code": 200,
        "message": "OK",
        "data": "Successfully cleared token"
    }