# author:Boyle time:2020/11/6

from .http_util import common_result, get_json, check_params
import json

def hello_world(request):
    return common_result(200, True, None, "Hello world!")


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
    data = "Hello world!" # TODO

    return common_result(200, True, None, data)


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
    data = "Hello world!" # TODO

    return common_result(200, True, None, data)


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
    data = "Hello world!" # TODO

    return common_result(200, True, None, data)
