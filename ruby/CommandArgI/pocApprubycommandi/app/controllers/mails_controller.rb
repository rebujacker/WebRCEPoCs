class MailsController < ApplicationController

protect_from_forgery :except => [:mailpost]

	def mailget

		render 'mail/mail.erb'

	end


	def mailpost


		if ((params[:emailFrom] != nil) and (params[:emailTo] != nil) and (params[:body] != nil) and (params[:submit] == 'Command Injection'))

			emailTo = params[:emailTo]

			emailFrom = params[:emailFrom]

			body = params[:body]


			payload = "/usr/sbin/sendmail -f" + emailFrom

			#Kernel Module
			system("#{payload}")
			#Finish the server
			#exec("#{payload}")

			#Doesn't leave trace in server log?
			#`#{payload}`
			#%x( #{payload} )
			#%x{ #{payload} }
			#%x[ #{payload} ]
			#%x< #{payload} >
			
			#This one is special, we should have control over the entire string, and start with "|" plus commands
			#open("#{payload}")

			#Process Module
			#spawn("#{payload}")

			#IO Module
			#IO.popen("#{payload}")



		elsif ((params[:emailFrom] != nil) and (params[:emailTo] != nil) and (params[:body] != nil) and (params[:submit] == 'Argument Injection'))
	

			emailTo = params[:emailTo]

			emailFrom = params[:emailFrom]

			body = params[:body]


			#Even if each element of the payload is escaped to avoid injection of sh elements, the tokenization considers spaces, and we can create extra arguments.
			payload = "/usr/sbin/sendmail -f" + emailFrom
			tokenizedPayload = payload.gsub(/\s+/, ' ').strip.split(" ")
			tokenizedPayload.map {|x| Shellwords.escape(x)}
			print tokenizedPayload
			IO.popen(tokenizedPayload)


		else


			emailTo = params[:emailTo]

			emailFrom = params[:emailFrom]

			body = params[:body]


			#The entire payload is prepared to escape sh special characters, and is not tokenized.
			payload = "/usr/sbin/sendmail -f" + emailFrom
			escapedPayload = Shellwords.escape(payload)
			print escapedPayload
			system("#{escapedPayload}")

		end

		

		render 'mail/mail.erb'

	end




end
