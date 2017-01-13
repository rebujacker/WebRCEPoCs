from django.http import HttpResponse
from django.views.decorators.csrf import csrf_exempt
from django.shortcuts import render
from django.template import loader
import base64
import cPickle

#Never leave without csrf-token any function, this is a POC!
@csrf_exempt
def index(request):
	session = ''
	unserSession = ''

	if request.method == 'GET':

	
		try:
			session = request.COOKIES['session']
			print request.COOKIES
			unserSession = cPickle.loads(base64.b64decode(session))
		except Exception:
			pass
		
		if unserSession == 'admin':
			return render(request,'logged.html')
		
		return render(request,'login.html')

	elif request.method == 'POST':
#Main PoC Vulnerability as follows
		try:
			session = request.COOKIES['session']
			unserSession = cPickle.loads(base64.b64decode(session))
		except Exception:
			pass
		

		if unserSession == 'admin':
			return render(request,'logged.html')

		email = request.POST.get('email')
		password = request.POST.get('password')
#Never use hardcoded credentials, this is a POC!
		if ((email == "pic@poc.puc") and (password == "test1234")):
			template = loader.get_template('logged.html')
			response = HttpResponse(template.render(request))
			#Never set a unsafe cookie like this, this is a POC!
			cookie = base64.b64encode(cPickle.dumps('admin'))
			response.set_cookie('session', cookie)
			#response.set_cookie('session',base64.b64encode("blabla"))
			return response

		return render(request,'login.html')

	else:
		return HttpResponse("NOTFOUND")





