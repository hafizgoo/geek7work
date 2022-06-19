# geek7work
## 1.（必做）按自己设计的表结构，插入 100 万订单模拟数据，测试不同方式的插入效率

#### jdbc 批量插入 100 万数据时间大概在100s左右

#### 去除掉自动提交
#### Hikari 批量插入 100 万数据
#### 时间大概在 80s 左右

#### 注意点
#### 在数据库连接url上增加了 rewriteBatchedStatements=true 这个参数，会让数据插入性能提升好几倍

## 2.（必做）读写分离 - 动态切换数据源版本 1.0
 [ 动态切换数据源版本 1.0](https://github.com/hafizgoo/geek7work/tree/main/mase/src/main/java/com/hafizgoo/masterslave)
## 3.（必做）读写分离 - 数据库框架版本 2.0
 [数据库框架版本 2.0](https://github.com/hafizgoo/geek7work/tree/main/ssmastersalve/src/main/java/com/hafizgoo/ssmastersalve)
