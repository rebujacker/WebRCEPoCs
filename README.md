# Web-RCE-PoC-s
Most common theorical Web RCE's with some exploits and PoC's to practise with (not real CVE's)


Python:


	1.Unsafe Deserialization of untrusted input data:

		a. Pickle.load(): RCE through Deserialization Using uncontrolled Pickle.load Funtion

Java:

	1.Unsafe Deserialization of untrusted input data:

		a. readObject(): RCE through Deserialization Using uncontrolled input in .readObject() method of a serializable class. Control over this object atributes with combination of the existence of Dangerous Invocation Handlers make possible the reproduction of this vulnerability. Beanshell is included in this PoC (CVE-2016-2510)

	2. Command Injection and Argument Injection:

		Dangerous Functions:
		Runtime.getRuntime().exec(
		new ProcessBuilder(cmd).start();

		Defense:
		Not calling directly bash/sh/cmd as process
		Using String[] instead of normal String craft for Runtime or using ProcessBuilder

PHP:

	1.Command Injection and Argument Injection:

		Dangerous Functions:
		popen(
		shell_exec(
		exec(
		passthru(
		system(
		proc_open(
		`command`
					
		Defense:
		escapeshellcmd()
		escapeshellarg()

		==> Passing a String from userInputs to these functions without escaping could be lethal.In this PoC I will try to create a real use case where we can play with these functions and realise the problems involved.
	