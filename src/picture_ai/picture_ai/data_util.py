# author:Boyle time:2020/11/13

import numpy as np


def np_to_str(np_arr):
    """
    将NumPy向量转换为字符串
    :param <numpy.array> np_arr: NumPy向量
    :return: <str> str_arr: 字符串
    """
    str_arr = ",".join(str(value) for value in np_arr.tolist())
    return str_arr


def str_to_np(str_arr):
    """
    将字符串转换为NumPy向量
    :param <str> str_arr: 字符串
    :return: <numpy.array> np_arr: NumPy向量
    """
    list_arr = [float(value) for value in str_arr.split(',')]
    return np.array(list_arr)
