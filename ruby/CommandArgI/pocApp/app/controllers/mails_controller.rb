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


			#Similar to other PoC's, we only need to change the payload to:
			#payload = "/usr/sbin/sendmail -fss@email.com -be '${run{/usr/bin/wget${substr{10}{1}{$tod_log}}http://127.0.0.1/test}}'"
			#EmailFrom should be filtered to escape bash special chars
			
			payload = "/usr/sbin/sendmail -f" + emailFrom

			system("#{payload}")


		else


			emailTo = params[:emailTo]

			emailFrom = params[:emailFrom]

			body = params[:body]

		end

		

		render 'mail/mail.erb'

	end




end
