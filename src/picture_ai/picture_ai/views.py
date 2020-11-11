# author:Boyle time:2020/11/6
from django.http import HttpResponse


def hello_world(request):
    return HttpResponse("Hello world!")


def image_insert_view(request):
    pass


def image_search_view(request):
    pass


def image_recognition_view(request):
    pass
