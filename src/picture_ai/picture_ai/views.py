# author:Boyle time:2020/11/6
from django.http import HttpResponse

def hello_world(request):
    return HttpResponse("Hello world!")