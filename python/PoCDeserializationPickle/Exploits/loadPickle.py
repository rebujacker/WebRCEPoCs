import pickle
import os

def read(filename, filedir='./'):
	filename = filedir + filename
	print filename
	with open(filename, 'rb') as f:
		loadobj = pickle.load(f)
		print loadobj

read("file","")
