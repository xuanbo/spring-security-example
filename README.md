#   说明

spring security学习，采用Java Config配置。

##  1.实现的功能

*   自定义认证，用户是否被禁用、账号是否过期、登录尝试失败5次锁定用户账号
*   加密
*   验证码
*   RememberMe，数据库持久化Token
*   用户跟角色信息存储在数据库
*   全局安全方法

##  2.进一步要实现的功能

*   使用AJAX替代登录的表单提交。ajax提交请看这个例子：[spring-security-example-ajax](https://github.com/xuanbo/spring-security-example-ajax)
*   权限跟资源存储在数据库

##  3.闲的蛋疼

*   实现一个权限的后台管理系统，角色、权限、资源的分配

##  4.总结

需要深入了解Spring Security登录、认证、授权的流程、原理、代码实现，不然出现问题很难提高。