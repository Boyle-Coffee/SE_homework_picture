# author:Boyle time:2020/11/6

import json
from django.shortcuts import render

from .http_util import common_result, get_json, check_params
from .image_db import image_orm
from .image_reco import image_reco
from .image_violate import image_nsfw

def hello_world(request):
    return render(request, "API2.html")


def image_insert_view(request):
    # 接口要求的参数
    require_params = ["pid", "url"]

    # 获取参数
    if request.method == 'POST':  # 判断请求方式是否正确
        post_json = json.loads(request.body)
        print(type(post_json), post_json)
        if not isinstance(post_json, dict):  # 判断json格式是否正确
            message = "input json is error"
            return common_result(400, False, message, None)
    else:
        message = "the method of request is error"
        return common_result(400, False, message, None)

    json_params = list(post_json.keys())
    isSuccess, message = check_params(require_params, json_params)  # 判断json参数是否正确
    if not isSuccess:
        return common_result(400, False, message, None)
    pid = post_json["pid"]
    url = post_json["url"]
    print(type(image_orm))
    code, isSuccess, message = image_reco.image_insert(pid, url) # 录入

    return common_result(code, isSuccess, message, None)


def image_search_view(request):
    # 接口要求的参数
    require_params = ["url"]

    # 获取参数
    if request.method == 'POST':  # 判断请求方式是否正确
        post_json = json.loads(request.body)
        print(type(post_json), post_json)
        if not isinstance(post_json, dict):  # 判断json格式是否正确
            message = "input json is error"
            return common_result(400, False, message, None)
    else:
        message = "the method of request is error"
        return common_result(400, False, message, None)

    json_params = list(post_json.keys())
    isSuccess, message = check_params(require_params, json_params)  # 判断json参数是否正确
    if not isSuccess:
        return common_result(400, False, message, None)

    url = post_json["url"]
    code, isSuccess, message, data = image_reco.image_search(url) # 搜索

    return common_result(code, isSuccess, message, data)


def image_delete_view(request):
    # 接口要求的参数
    require_params = ["pid"]

    # 获取参数
    if request.method == 'POST':  # 判断请求方式是否正确
        post_json = json.loads(request.body)
        print(type(post_json), post_json)
        if not isinstance(post_json, dict):
            message = "input json is error"
            return common_result(400, False, message, None)
    else:
        message = "the method of request is error"
        return common_result(400, False, message, None)

    json_params = list(post_json.keys())
    isSuccess, message = check_params(require_params, json_params)  # 判断json参数是否正确
    if not isSuccess:
        return common_result(400, False, message, None)

    pid = post_json["pid"]
    code, isSuccess, message = image_reco.image_delete(pid)

    return common_result(code, isSuccess, message, data)


def image_recognition_view(request):
    # 接口要求的参数
    require_params = ["url"]

    # 获取参数
    if request.method == 'POST':  # 判断请求方式是否正确
        post_json = json.loads(request.body)
        print(type(post_json), post_json)
        if not isinstance(post_json, dict):  # 判断json格式是否正确
            message = "input json is error"
            return common_result(400, False, message, None)
    else:
        message = "the method of request is error"
        return common_result(400, False, message, None)

    json_params = list(post_json.keys())
    isSuccess, message = check_params(require_params, json_params)  # 判断json参数是否正确
    if not isSuccess:
        return common_result(400, False, message, None)

    url = post_json["url"]
    code, isSuccess, message, data = image_nsfw.image_func(url) # 搜索

    return common_result(code, isSuccess, message, data)

