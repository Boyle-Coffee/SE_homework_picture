from django.contrib import admin
from PictureModel.models import Image_info


class ImageAdmin(admin.ModelAdmin):
    list_display = ('pid', 'state','feature')

admin.site.register(Image_info, ImageAdmin)
