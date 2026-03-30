
**1.12. REFLECTION: Use the terms “subtype”, “polymorphism” and “dynamic method dispatch” 
to explain why Main.run works correctly for ANY of your Logger implementations.**
<br><br>
The run() function in Main works correctly for all Logger implementations because it accepts any class that implements
the Logger interface as an argument. The log method is declared in the interface, and implemented in ConsoleLogger (and its subclasses). 
As long as it is a Logger (which **requires** an implementation of the log method), the compiler trusts there is a log method in place 
for whatever type of Logger it is. Then, dynamic method dispatch is used by the JRE to determine which exact log() method should be called based on the
subtype of ConsoleLogger it's being called on, and selects the most specific option it finds. 
This is an example of polymorphism in action - we're accessing and dealing with different entities under the same public interface. 

**1.13. REFLECTION: Why can the Logger.formatMessage method have a default implementation? 
Why must the Logger.log method be abstract?**
<br><br>
Logger.formatmessage can have a default implementation because Logger is an interface. In this context, default methods are used to 
specify the behaviour of the method in case implementers of the interface don't provide their own. 
<br><br>
Logger.log must be abstract because that's how you create the "contract" where you tell the compiler that all implementations of the Logger interface WILL have a log method (unless the inheritor is also abstract.)
It allows the implementation of log to (potentially) be distinct from subclass to subclass...polymorphism ! 

**1.14.3. REFLECTION: Among all the Loggers you have implemented, ONLY MemoryLogger objects 
can be passed to the Main.export method. Why? Why MUST the declaration for the MemoryLogger use
‘MemoryLogger’ instead of just ‘Logger’ as its type? (Try changing the declaration line to ‘Logger’ and see what happens.)**
<br><br>
The Main.export method expects an Exportable argument.
The compile-time type of the MemoryLogger when declared as _Logger logger = new MemoryLogger();_ is Logger-> Logger does not have an export method. 
However, if you change the declaration back to _MemoryLogger logger = new MemoryLogger();_ the compiler recognizes that MemoryLogger is able to call 
the export() method as it inherits it through the Exportable interface that it implements. 
