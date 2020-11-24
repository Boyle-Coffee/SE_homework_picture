
import numpy as np
import traceback
import cv2
import os

from .models.extract_cnn_vgg16_keras import VGGNet
from .data_util import *
from .image_db import image_orm
from .image_util import image_imread


"""
以图搜图功能
"""

weigh_path = "picture_ai/models/pre_train/"


class ImageRocognition():

    def __init__(self, score_threshold=0.75,net_type="VGG16"):
        """
        Parameters:
         score_threshold: 相似度得分阈值，当相似度最大的图片的得分低于此值，则视为图像库中不包含查询图片，将查询图片录入到图像库中
                默认值为 0.75
         db_info: 数据库信息
         table_name: 表格名称，
                     默认：image_information
         recognition_pretrained: 预训练好的提取图像特征网络
                               默认：VGG16网络
                               可选：VGG16、ResNet50、DenseNet121
        """
        self.score_threshold = score_threshold
        self.net_type = net_type
        self.net = self._load_reconition_model()
        self.orm = image_orm

    def _load_reconition_model(self):
        # 加载人脸特征提取模型
        if self.net_type == "VGG16":
            model_path = os.path.join(weigh_path, "vgg16_weights_tf_dim_ordering_tf_kernels_notop.h5")
        elif self.net_type == "ResNet50":
            model_path = os.path.join(weigh_path, "resnet50_weights_tf_dim_ordering_tf_kernels_notop.h5")
        elif self.net_type == "DenseNet121":
            model_path = os.path.join(weigh_path, "densenet121_weights_tf_dim_ordering_tf_kernels_notop.h5")
        else:
            raise ValueError("The pretrained model is wrong")
        recognition_net = VGGNet(self.net_type, model_path=model_path)
        return recognition_net

    def image_insert(self, pid, url):
        """
        插入图片
        :param <str> pid: 图片编号
        :param <str> url: 图片地址
        :return: common result
        """
        try:
            image_rgb = image_imread(url)  # 获取图片
            if image_rgb is None:
               message = "error happen when read image"
               return 400, False, message
            img_feat = self.net.extract_feat(image_rgb)  # 提取图片特征
            feature_str = np_to_str(img_feat)
            result = self.orm.insert_data(pid, feature_str)  # 存入数据库
            if not result:
                msg = "An error occurred while inserting data into the database"
                return 500, False, msg
            else:
                return 200, True, None
        except Exception as e:
            print(traceback.format_exc())
            return 500, False, "some errors happened when calculate"

    def image_search(self, url):
        """
        以图搜图功能
        :param <str> url: 图片地址
        :return: common result
            <list> pids 相似图片id
        """
        try:
            image_rgb = image_imread(url)  # 获取图片
            if image_rgb is None:
                message = "error happen when read image"
                return 400, False, message, None
            img_feat = self.net.extract_feat(image_rgb)  # 提取图片特征

            db_pids, db_feat = self.orm.load_data() # 加载图片特征库
            scores = np.dot(img_feat, db_feat.T)
            match_pids = np.array(db_pids)[scores>=self.score_threshold].tolist()[0]

            return 200, True, None, match_pids
        except Exception as e:
            print(traceback.format_exc())

            return 500, False, "some errors happened when calculate", None

    def image_delete(self, pid):
        """
        删除图片接口
        :param <str> pid: 图片id
        :return: common result
        """
        try:
            result = self.orm.delete_data(pid)
            if not result:
                msg = "An error occurred while delete data from the database"
                return 500, False, msg

            return 200, True, None
        except Exception as e:
            print(traceback.format_exc())

            return 500, False, "some errors happened when calculate"


image_reco = ImageRocognition()