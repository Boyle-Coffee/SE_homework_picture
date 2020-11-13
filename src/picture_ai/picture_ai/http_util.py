# author:Boyle time:2020/11/11
import json
from django.http import HttpResponse

def common_result(code, isSuccess, message, data):
    """
    common result格式 json
    :param <int> code: 状态码
    :param <bool> isSuccess: 是否成功
    :param <str> message: 返回信息
    :param data: 数据
    :return: <dict> common result格式
    """
    try:
        result = {
            "code": code,
            "isSuccess": isSuccess,
            "message": message,
            "data": data
        }
        return HttpResponse(json.dumps(result))
    except Exception as e:
        result = {
            "code": 500,
            "isSuccess": False,
            "message": "result of json is error",
            "data": None
        }
        return HttpResponse(json.dumps(result))


def get_json(body):
    """
    获取 json 数据，
    :param body: 请求体
    :return: <dict> json数据
    """
    try:
        return json.loads(body.decode().replace("'", "\""))
    except Exception as e:
        return None


def check_params(require_params, json_params):
    """
    检查接口获取的参数是否正确
    :param <list> require_params: 接口要求的参数
    :param <list> json_params: 接口获取的 json 参数
    :return:
        <bool> isSuccess: "是否检查成功"
        <str> message: "返回错误信息，检查成功返回 None"
    """
    for param in json_params:
        if param not in require_params:
            message = "'%s' is an invalid param"%(param)
            return False, message

    for param in require_params:
        if param not in json_params:
            message = "request missing required param '%s'"%(param)
            return False, message

    return True, None
