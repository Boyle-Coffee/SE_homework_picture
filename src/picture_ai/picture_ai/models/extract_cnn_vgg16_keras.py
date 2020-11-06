# author:Boyle time:2020/11/6
import numpy as np
from numpy import linalg as LA
from PIL import Image as pil_image

from keras.applications.vgg16 import VGG16
from keras.applications.vgg16 import preprocess_input as preprocess_input_vgg

from keras.applications.resnet50 import ResNet50
from keras.applications.resnet50 import preprocess_input as preprocess_input_resnet

from keras.applications.densenet import DenseNet121
from keras.applications.densenet import preprocess_input as preprocess_input_densenet

import cv2


# 加载模型的方法
load_model_func = {}
load_model_func["VGG16"] = VGG16
load_model_func["ResNet50"] = ResNet50
load_model_func["DenseNet121"] = DenseNet121


# 处理输入图像的方法
preprocess_input_func = {}
preprocess_input_func["VGG16"] = preprocess_input_vgg
preprocess_input_func["ResNet50"] = preprocess_input_resnet
preprocess_input_func["DenseNet121"] = preprocess_input_densenet


class VGGNet:
    def __init__(self,recognition_model_type="VGG16",model_path = None,weight=None):
        self.input_shape = (224, 224, 3)
        self.weight = weight  # 模型的权重
        self.model_path = model_path  # 训练模型的位置
        self.recogniton_model_type = recognition_model_type  # 模型类型
        self._load_model()

    def _load_model(self):
        """
        加载模型
        """
        if self.recogniton_model_type in load_model_func:
            self.model = \
                load_model_func[
                    self.recogniton_model_type
                ](weight=self.weight,
                  input_shape = self.input_shape,
                  pooling = "max",
                  include_top=False
                  )
        else:
            raise ValueError("The pretrained model is wrong")
        try:
            self.model.predict(np.zeros((1, 224, 224, 3)))
        except Exception as e:
            pass

    def extract_feat(self, image):
        """
        利用vgg16/Resnet模型提取特征归一化特征向量
        （最后一层卷积特征）
        :param <np.array> image: RGB图片
        :return:
            <np.array> norm_feat: 归一化后的特征数据
        """
        target_size = (self.input_shape[0], self.input_shape[1])
        image = cv2.resize(image, dsize=target_size)  # 裁剪图像
        image = np.expand_dims(image, axis=0)  # 增加一个维度

        if self.recogniton_model_type in preprocess_input_func:
            image = preprocess_input_func[
                self.recogniton_model_type
            ](image)
        else:
            raise ValueError("The pretrained model is wrong")
        feature = self.model.predict(image)
        norm_feat = feature[0] / LA.norm(feat[0])

        return norm_feat