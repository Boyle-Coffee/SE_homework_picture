# author:Boyle time:2020/11/12

import numpy as np

from PictureModel.models import Image_info
from .data_util import *


class Image_db:

    def __init__(self):
        self.orm = Image_info

    def insert_data(self, pid, feature):
        """
        向数据库输入数据
        :param <str> pid: 图片id
        :param <str> feature: 图片特征向量
        :return: 插入成功与否
        """
        try:
            image_data = self.orm(pid=pid, feature=feature)
            image_data.save()
            return True
        except:
            return False

    def load_data(self):
        """
        加载所有数据
        :return:
            <list> pids: 图片编号列表
            <np.array> feature_vec: mxn二维数组， m为图片张数，n为向量大小
        """
        list = self.orm.objects.filter(state=True)
        print(type(list), list)
        uids = []
        feature_vec = None
        for obj in list:
            pid, feature = obj.pid, obj.feature
            uids.append(pid)
            feature_vec = str_to_np(feature) if feature_vec is None else np.vstack((feature_vec, str_to_np(feature)))
        return uids, feature_vec


image_orm = Image_db()
