# author:Boyle time:2020/11/12
from PictureModel.models import image_info

class Image_db:

    def __init__(self):
        self.orm = image_info

    def insert_data(self, pid, feature):
        """
        向数据库输入数据
        :param pid:
        :param feature:
        :return:
        """
        pass

    def load_data(self):
        """
        加载所有数据
        :return:
        """
        pass