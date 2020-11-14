# author:Boyle time:2020/11/13
import os
import traceback

from .models.nsfw_model import OpenNsfwModel
from .image_util import load_image

weigh_path = "picture_ai/models/pre_train/"


class ImageNsfw():

	def __init__(self, model_file, score_threshold=0.7):
		self.score_threshold = score_threshold
		self.model_path = os.path.join(weigh_path,model_file)
		self._load_reconition_model()

	def _load_reconition_model(self):
		self.model = OpenNsfwModel()
		self.model.build(self.model_path)

	def image_func(self, url):
		try:
			image = load_image(url)
			if image is None:
				message = "error happen when read image"
				return 400, False, message, None
			predictions = self.model.predict(image)
			print(predictions[0])
			data = True if predictions[0][1] > 0.7 else False
			return 200, True, None, data
		except Exception as e:
			print(traceback.format_exc())
			return 500, False, "some errors happened when calculate",None


image_nsfw = ImageNsfw("open_nsfw-weights.npy")