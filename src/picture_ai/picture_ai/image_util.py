import numpy as np
import skimage
import skimage.io
from PIL import Image
from io import BytesIO
import cv2
import requests


VGG_MEAN = [104, 117, 123]


def image_imread(url):
	"""
	读取图片
	:param <str> url: 图片地址
	:return: <np.array> 图片
	"""
	try:
		image_bgr = cv2.imdecode(np.fromfile(url, dtype=np.uint8), -1)  # 获取图片
	except Exception as e:
		image_bgr = cv2.imread(url)
	if image_bgr  is not None:
		image_rgb = cv2.cvtColor(image_bgr, cv2.COLOR_BGR2RGB)
		return image_rgb
	for i in range(10):
		try:
			file = requests.get(url)
			image = cv2.imdecode(np.fromstring(file.content, np.uint8), 1)
			return image
		except Exception as e:
			continue
	return None


def load_image(url, expand_dims=True):
	"""
	加载图片
	:param <str> image_path: 图片路径
	:param <bool> expand_dims: 是否增加维度
	:return: 
	"""
	image_rgb = image_imread(url)
	if image_rgb is None:
		return None

	im = Image.fromarray(np.uint8(image_rgb))

	if im.mode != "RGB":
		im = im.convert('RGB')

	imr = im.resize((256, 256), resample=Image.BILINEAR)

	fh_im = BytesIO()
	imr.save(fh_im, format='JPEG')
	fh_im.seek(0)

	image = (skimage.img_as_float(skimage.io.imread(fh_im)).astype(np.float32))

	H, W, _ = image.shape
	h, w = (224, 224)

	h_off = max((H - h) // 2, 0)
	w_off = max((W - w) // 2, 0)
	image = image[h_off:h_off + h, w_off:w_off + w, :]

	# RGB to BGR
	image = image[:, :, :: -1]

	image = image.astype(np.float32, copy=False)
	image = image * 255.0
	image -= np.array(VGG_MEAN, dtype=np.float32)

	if expand_dims:
		image = np.expand_dims(image, axis=0)

	return image