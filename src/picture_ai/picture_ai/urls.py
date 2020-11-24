"""picture_ai URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/3.1/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from django.conf.urls import url

from . import views

urlpatterns = [
    url(r'^$', views.hello_world),
    path('admin/', admin.site.urls),
    url(r'^imageInsert$', views.image_insert_view),  # 以图搜图图像录入
    url(r'^imageSearch$', views.image_search_view),  # 以图搜图相似图查找
    url(r'^imageDelete$', views.image_delete_view),  # 以图搜图图像删除
    url(r'^imageRecognition$', views.image_recognition_view)  # 违规图像识别
]
