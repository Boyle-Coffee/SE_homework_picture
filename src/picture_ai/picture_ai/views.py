# author:Boyle time:2020/11/6
from django.http import HttpResponse

def hello_world():
    return HttpResponse("Hello world!")