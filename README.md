# SE_homework_picture
软件工程团队项目代码及文档仓库

## 提交信息规范

commit message 包括两个部分：Header、Body

Header：描述当前commit的类别，主要是以下几种

- 功能：提交功能点。
- 修复：修复bug。
- 重构：代码重构，未新增任何功能和修复任何bug。
- 配置：修改项目工程文件配置。
- 文档：修改文档

Body：对本次commit的概要描述

## issue 命名规范

命名包括两部分：Header、Body

Header：所属功能模块

Body： issue 的简要描述

其他属性还包括 description 和 label，label 采用 GitHub 自带的类别，如下：

![](md_image\issue_label.jpg)

详细参考[ issue 规范](https://zhuanlan.zhihu.com/p/52370003)

## 关于项目和文档

### 仓库目录结构

- SE_homework_picture
  - configs
  - dbscripts
  - docs
  - src

各目录作用如下：

#### SE_homework_picture

> 仓库名称，`SE_homework_`表示项目性质为软件工程作业，`_picture`表示该项目的主要应用场景

#### configs

> 在开发的时候，项目是部署在开发环境中的，所以项目中的一些配置文件，比如：数据库连接配置文件，都是用的开发环境的数据库连接配置，项目要部署上线的时候，我们需要把开发环境的数据库连接配置替换成生产环境的数据库连接配置，所以这个目录可以用来存**生产环境的相关配置文件**。

> 之所以将测试环境/开发环境/生产环境分开是因为开发过程中，需要对数据库中的一些数据进行测试或者修改，如果不和生产环境分开，**会“污染”生产环境的数据**。

#### dbscripts

> 在开发过程中，**每次开发过程中涉及到要执行一些脚本**，比如：开发过程中，要新建一个表，建表语句就要保存在这个目录里面，待开发完毕上线的时候，就需要在生产环境中执行这个建表语句。

#### docs

> 这里存放**项目的相关文档**，比如：需求说明书，会议纪要，上线手册等。

#### src

> 这里存放**项目源码**。
