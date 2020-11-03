# 代码规范

>Author : Boyle
>
>Start Date : 2020-09-14
>
>Last Modified : 2020-09-29

> 该代码规范主要参考 PEP8 编码规范
>
> https://legacy.python.org/dev/peps/pep-0008/#function-annotations
>
> https://www.cnblogs.com/ajianbeyourself/p/4377933.html

## 代码布局

> 参考 PEP8 

## 表达式和语句中的空格

> 参考 PEP8 

## 注释

> 参考 PEP8 

除了 PEP8 的内容外，这里再补充几个规范：

**函数和方法**

下文所指的函数,包括函数, 方法, 以及生成器.

- 一个函数必须要有文档字符串, 除非它满足以下条件:

  1. 外部不可见
  2. 非常短小
  3. 简单明了

- 文档字符串应该包含函数**做什么**、以及函数**输入**和**输出**的描述，必要时列出与接口有关的所有异常。通常不应该包含**怎么做**，除非一些复杂的算法

  ```python
  def func(url, arg2):
      """
  	根据url路径获取图片
      :param url: 图片的url路径
      :param arg2: 参数2
      :return: 
      	获取的图片
      	{"image":"获取的图片，BGR格式"}
      :raise:
          IOError: An error occurred accessing the bigtable.Table object.
      """
      image = cv2.imread(url)
      return image
  ```

**类**

类应该在其定义下有一个用于描述该类的文档字符串. 如果你的类有公共属性(Attributes), 那么文档中应该有一个属性(Attributes)段. 并且应该遵守和函数参数相同的格式.

```python
class SampleClass(object):
    """Summary of class here.

    Longer class information....
    Longer class information....

    Attributes:
        likes_spam: A boolean indicating if we like SPAM or not.
        eggs: An integer count of the eggs we have laid.
    """

    def __init__(self, likes_spam=False):
        """Inits SampleClass with blah."""
        self.likes_spam = likes_spam
        self.eggs = 0
```

**其他**

1. 内容要简单、明了、含义准确，不要出现形容词，防止注释的多义性，错误的注释不但无益反而有害。

2. 注释不宜过多，不要注释那些那些显而易见的事情的内容，注释过多反而降低代码的可读性

3. 对于一些未解决的问题，可以写上以"TODO"开始的注释，紧跟着是用括号括起来的你的名字，最好在注释中包含一个截止日期（“2018年12月解决”）或等待一个特定事件的发生（“在识别代码完成后完成”）。

   ```python
   def func():
   	# TODO(Jodje): 获取图像，在识别代码完成后完成
       pass
   ```

## 命名规范

> 参考 PEP8 

除了 PEP8 的内容外，这里再补充几个规范：

### 最重要的原则

那些暴露给用户的API接口的命名，应该遵循反映使用场景而不是实现的原则。

### 命名风格

命名风格一般采用蛇形命名法，即使用下划线分隔的小写字母

### 命名约定

#### 应避免的名字

永远不要使用字母‘l’（小写的L），‘O’（大写的O），或者‘I’（大写的I）作为单字符变量名。 在有些字体里，这些字符无法和数字0和1区分，如果想用‘l’，用‘L’代替。

#### 包名和模块名

- 模块名采用蛇形命名法，即使用下划线分隔的小写字母，具体的命名规范针对不同项目在项目设计阶段经讨论确定
- 包名使用简短全小写的名字，但不建议用下划线。
- 各模块之间的接口函数应该在项目设计阶段讨论确定

#### 函数名

一般采用蛇形命名法，即使用下划线分隔的小写字母命名

#### 视图函数名

路由的视图函数名采用将对应路径的斜杆替换为下划线的命名方式，如：

```python
# flask
@app.route("hello/")
def hello():
    pass

@app.route("/image/entryInformation", methods=['POST'])
def image_entryInformation():
	pass

# django
urlpatterns = [
    path('hello/', views.hello),
    path('runoob/', views.runoob),
    path('image/entryInformation/', views.image_entryInformation),
]
```

### 编程建议

> 参考 PEP8 

