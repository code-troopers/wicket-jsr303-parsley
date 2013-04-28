# Wicket JSR303 ParsleyJs integration

[Parsley.js](https://github.com/guillaumepotier/Parsley.js) is a client-side form validation library ([documentation](http://parsleyjs.org/documentation.html)).

[Apache Wicket](http://wicket.apache.org) is a Java Web framework providing clean separation between markup and logic.

[JSR303 - Bean validation](http://beanvalidation.org/1.0/spec/) is a Java specification providing an easy way to define constraint on your model objects.

This project provides a simple way to get client side validation for POJOs annotated with Bean Validation's contraints.

# Setup on your project

Add the following Maven dependency

    <dependency>
        <groupId>com.code-troopers</groupId>
        <artifactId>wicket-jsr303-parsley</artifactId>
        <version>0.1</version>
    </dependency>

To use it in your Wicket application, you will need to register Parsley with your application.
Basically, you will add this code in your Application#init() method :

    @Override
    public void init(){
        super.init();
        Parsley.register(this);
    }

For every form that you want to validate using Parsley, you will need to add ParsleyFormBehavior to your Form :

    Form form = new Form("form");
    form.add(new ParsleyFormBehavior());

Every FormComponent in the specified Form will get a correct validator (both client and server side).
Please notice that you will need to use a model implementing IPropertyReflectionAwareModel in order for the tool to read annotation metadata.

# Bug tracker

Have a bug? Please create an issue here on GitHub!

https://github.com/code-troopers/wicket-jsr303-parsley/issues


# Special notes

Many thanks to Martin Grigorov for the blog article which inspired me : http://wicketinaction.com/2013/04/server-and-client-side-validation/

The implementation provided here is open for pull request or further integration into WicketStuff.

# Copyright and license

Copyright 2012 Code-Troopers.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this work except in compliance with the License.
You may obtain a copy of the License in the LICENSE file, or at:

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.