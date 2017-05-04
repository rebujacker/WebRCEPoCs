# Web-RCE-PoC-s
Most common theorical Web RCE's with some exploits and PoC's to practise with (not real CVE's)


Python:


	1.Unsafe Deserialization of untrusted input data:

		a. Pickle.load(): RCE through Deserialization Using uncontrolled Pickle.load Funtion

Java:

	1.Unsafe Deserialization of untrusted input data:

		a. .readObject(): RCE through Deserialization Using uncontrolled input in .readObject() method of a serializable class. Control over this object atributes with combintion of the existence of Dangerous Invocation Handlers make possible the reproduction of this vulnerability. Beanshell is included in this PoC (CVE-2016-2510)
