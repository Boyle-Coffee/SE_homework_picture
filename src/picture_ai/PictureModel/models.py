from django.db import models

class Image_info(models.Model):
    pid = models.CharField(max_length=20, null=False)
    feature = models.TextField(max_length=20000, null=False)
    state = models.BooleanField(default=True)