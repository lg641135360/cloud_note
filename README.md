# json响应流程
请求-->DispatcherServlet
-->HandlerMapping
-->Controller
-->返回数据对象(string/user/list/Map)
-->JSON响应

怎么用？
- 导包 jackson
-- databind，core，annotations
- 配置注解驱动
-- <mvc:annotation-driven/>
- Controller方法上增加@ResponseBody

# 项目简介
## 项目概述
云笔记，是tmooc上的一个子项目，用于客户进行在线学习记录分享，收藏笔记，以及参与社区活动。
## 模块划分
- 用户模块：登陆 注册 修改密码 退出
- 笔记本模块
- 笔记模块
- 分享/收藏模块
- 回收模块
- 活动模块
## 设计思想
MVC设计思想
表现层:html + css + JQuery + ajax
控制层:springmvc
业务层:service组件
持久层:Dao组件
## 技术架构
1. 开发环境：windowsXP + Tomcat + mysql
2. 采用技术：java + jQuery + ajax + springmvc
            IOC + AOP + mybatis
            java：开发核心技术
            jquery：简化前端js($对象和API)
            ajax：局部处理页面，提升用户体验度
            spring(IOC/AOP)：管理相关组件
            IOC：负责管理Controller/service/dao，维护它们之间的关系
            AOP：面向切面编程，不修改原有代码，给系统增加新的功能
            mybatis：对数据库进行操作
## 整体规范
- 所有的请求ajax方式访问
- 前端页面使用HTML
- 请求结果采用JSON响应
  {status： ，"msg": ,"data": }

## 数据库构建
mysql -uroot -p

### 常用命令
show databases;                  //查看有哪些数据库
create database cloud_note;      //创建数据库
drop database cloud_note;        //删除数据库
use 数据库名;                     //连接数据库
show tables;                     //查看有哪些表
导入SQL文件
set names utf8;
source /home/soft01/cloud_note.sql;

E-r 实体-关系模型图
由业务分析产生，作为实体类和数据库设计的依据。

# 项目流程
## cmmi
前身是CMM，软件成熟度模型，认证。

分级：
初始级 - 可管理级

QA -- 评审

## 项目开发流程

### 项目模型：
        瀑布模型
### 阶段产品
项目总计划
#### 需求阶段
需求规格说明书 用户需求
#### 设计阶段
概要设计说明书
详细设计说明书

#### 编码阶段
需求源码
测试文档

#### 测试阶段
测试案例
测试报告

## 人员结构
项目经理
QA CM dev test 测试经理

SSM框架 springMVC + spring + Mybatis
spring-mybatis
# 创建云笔记项目环境

## 导包
将jackson和springmybatis的包导入。
别忘了servlet-api和配置xml的位置
```
<build>
  <finalName>cloud_note</finalName>
  <resources>
    <resource>
      <directory>src/main/java</directory>
      <includes>
        <include>**/*.xml</include>
      </includes>
    </resource>
  </resources>
</build>
```
## 添加配置文件
- conf/spring-mvc.xml
- conf/spring-mybatis.xml
- mapper/SQL文件
- web.xml
## 划分包结构
- com.lg.cloud_note.dao
- com.lg.cloud_note.service
- com.lg.cloud_note.Controller
- com.lg.cloud_note.pojo
- com.lg.cloud_note.util
## 将html目录下的内容拷贝到webapp下面

# 登陆功能
## 发送ajax请求
- 事件绑定
- 获取参数
- $.ajax发送请求
## 服务器处理
请求 -- DispatcherServlet
--> HandlerMapping
--> Controller.excute()
--> Service->Dao->cn_user
--> 返回数据
## ajax回调处理
- 成功：edit.html
- 失败：提示信息，重新登陆

# 注册功能
## 发送ajax请求
- 发送事件：注册按钮的单击
- 获取的参数：用户名 昵称 密码
- 请求地址：/user/add.do
## 服务器处理
请求 --> DispatcherServlet
--> HandlerMapping
--> Controller.excute
--> Service -- addUser
    //检查用户
    user = findByName(name)
    user != null
    result
    //添加用户
    createId
    处理password
    user.set()
    save(user)
    //设置result
--> Dao -- cn_user (insert)
1.UserDao(save(User))
2.UserMapper.xml(insert)
--> Json响应
## ajax回调处理
success:
注册成功
注册失败
error(异常):
提示："注册失败"

UUID
是一个生成字符串算法
用于生成数据库主键

为什么？

生成主键有两种方式：
在应用服务器端生成主键
在数据库端生成主键

# 笔记本显示功能
## 发送ajax请求
- 发送请求:进入到edit.html发送请求
- 请求参数:userId
- 请求地址:/book/loadBooks.do
## 服务器处理
/book/loadBooks.do
--> LoadBooksController.excute
--> BookService.loadUserBooks
- 1 BookService接口
      loadUserBooks
- 2 实现类里
      调用dao.findByUserId
- 3 实现类中构建
      Result结果

--> BookDao.findByUserId
- 1 Bookdao接口中定义findByUserId
- 2 配置BookMapper.xml

--> cn_notebook(select)
--> JSON响应
## ajax回调处理
success:
解析json数据，循环生成笔记本项(li)
```
<li class="online">
	<a  class='checked'>
     <i class="fa fa-book" title="online" rel="tooltip-bottom"></i> 默认笔记本
  </a>
</li>
```
error:
alert("笔记本加载失败");

---

## < script >标记互斥
- 要么使用src属性引用外部js文件
- 要么在标记内自己写js代码

# 笔记列表的显示
## 发送ajax请求
- 发送事件：笔记本li的点击
- 请求参数：笔记本的id
- 请求地址：/note/loadnotes.do
## 服务器端的处理
/note/loadnotes.do
--> LoadNoteController.excute

--> NoteService.loadBookNotes

1 定义接口文件NoteService - loadBookNotes(bookId)
2 实现类中重写方法
  noteDao.findByBookId()
  构建Result -- set方法
  setData = List<Map>

--> NoteDao.findByBookId(bookId)

1 接口文件定义方法
2 配置mapper文件
3 TestNoteDao

--> cn_note(select-bookId & cn_note_status='1')
## ajax回调的处理
success:
成功：解析json数据，生成笔记li，添加到笔记列表中
```
var $li = $(  
        '<li class="online">'+  
        '<a>'+  
        '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+  
          noteTitle +  
        '<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>'+  
        '</a>'+  
        '<div class="note_menu" tabindex="-1">'+  
        '<dl>'+  
        '<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'+  
        '<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'+  
        '<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'+  
        '</dl>'+  
        '</div>'+  
        '</li>');  
```
error：
提示：alert("笔记加载失败");

事件绑定：
静态绑定：元素.click
(function(){})    有可能绑定失败，当页面没有生成完，即绑定的为一个空元素。
动态绑定：
父元素.on
("click","li",fn)

元素.click();

Dao接口的传值特点：
```
public interface Dao{
  void fn();
  void fn(String str);
  void fn(Map map);
}
```
总结：mybatis中Dao方法要么没有参数，要么一个参数

UEdit
用js开发的开源组件

为什么？
通过js代码实现表单功能的增强

如何用？
1. 引用脚本
2. 实例化
3. 通过< script >指定位置

# 显示笔记信息
## 发送ajax请求
- 发送事件：笔记列表li元素的单击事件(动态绑定)
- 请求参数：笔记id(noteId)
- 请求地址：/note/loadnote.do
## 服务器处理
- Controller:
  LoadNoteController.excute(String noteId)
- Service
  NoteService.loadNote(String noteId)
- Dao
  NoteDao.findByNoteId(String noteId)
- cn_note(查询)
- Json响应
## ajax回调处理
success:
- 设置笔记标题(title)
- 设置笔记内容(body)
  um.setContent("str");
  um.getContent("str");
error:
alert("笔记本加载失败")

# 更新笔记信息
## 发送ajax请求
- 发送事件："保存笔记"按钮点击事件(静态)
- 请求参数：笔记Id 标题(title) 内容(body)
- 请求地址:/note/update.do
## 服务器处理
- UpdateNoteController(id,title,body)
- NoteService,updateNote(Note)
- NoteDao.update(Note)
- cn_note(更新)
## ajax回调处理
success:
- 对被更新的笔记名称进行更新
- 获取笔记列表中的li元素
```
<li><a class="checked"></a></li>
var $li = $('#note_ul a.checked').parent();
```

# 使用alert
## 如何显示
- 通过load，加载html
- 通过show，显示黑化背景

# 创建笔记本
## 发送ajax请求
- 发送事件：对话框中的“创建按钮的单击事件(动态)”
- 请求参数：笔记本名称和用户Id
- 请求地址：/book/add.do
## 服务器处理
- AddBookController.excute
- BookService.addBook(bookName,userId)
- BookDao.save(bookName,userId)
- cn_notebook(insert)
- json响应
## ajax回调处理
success:
- 关闭对话框
- 添加一个笔记本li
- 提示创建笔记本成功

error:
- 提示创建笔记本失败

# 创建笔记
## 发送ajax请求
- 发送事件：对话框中的“创建按钮的单击事件(动态)”
- 请求参数：笔记本名称 笔记本id 用户id
- 请求地址：/note/add.do
## 服务器处理
- AddNoteController.excute
- NoteService.addNote(noteName,userId)
- NoteDao.save(bookName,userId)
- cn_note(insert)
- json响应
## ajax回调处理
success:
- 关闭对话框
- 解析json数据，生成一个li元素添加到笔记列表
  1. 获取noteTitle
  2. 获取noteId
- 提示创建笔记成功

error:
- 提示创建笔记失败

# 显示笔记下拉菜单
- 显示菜单(div.slideDown)
- 防止冒泡(return false)
```
//笔记下拉菜单
$('#note_ul').on("click",".btn_slide_down",function(){
    //隐藏笔记菜单
	  $('#note_ul div').hide();
		//显示点击菜单
    var note_menu =
      $(this).parents("li").find("div");
    note_menu.slideDown(1000);
    return false;
});
$('body').click(function () {
    $('#note_ul div').hide();
});
```

# 笔记的分享
## 发送请求
- 发送事件：点击"分享"
- 请求参数：noteId
- 请求地址：/share/add.do
## 服务器处理
- 创建Share类
- Controller
- Service
- Dao-insert
- cn_share
## 回调处理
success:
- 提示分享成功
- 增加图标处理
```
<i class="fa fa-sitemap">
```
error：
- 提示分享失败

# 分享笔记搜索功能
## 功能描述：用户输入搜索关键词，然后点击回车，触发查询

## 发送ajax请求
- 发送事件：输入关键词后，点回车
- 请求参数：输入的关键词
- 请求地址：/share/search.do
## 服务器请求
- Controller
- Service
- Dao 
- cn_share(select)
```
select * from cn_share like '%关键词%';
select * from cn_share like #{title} limit #{begin},3
m:记录的位置
n:每页显示的最大记录数
```
## ajax回调处理
success:
- 显示搜索笔记结果列表(pc_part_6)
- 将解析后的搜索结果，添加到列表中

error:
- 提示搜索失败
```
$('#InputId').keydown(function(event){
    var code = event.keyCode;
    if(code==13){
      发送ajax请求
    }
});
```
