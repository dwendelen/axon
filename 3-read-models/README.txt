1 USER AGGREGATE

A user has a name and an email address.
It is possible to register a user and to update his/her email address.

Complete the classes to implement these features.
All tests should run green at the end of this exercise.

Tips:
 - @AggregateIdentifier
 - @TargetAggregateIdentifier


2 EVENT LISTENERS

When a user registers, a mail is sent to the new users email address.
There also needs to be send a mail when the email address is changed.
It is allowed to use the same message for both cases.

Create a new listener that calls the mail client in Application.
Also try to create a unit test for the listener.
All tests should run green at the end of this exercise.

Tips:
 -  MailClientMock


3 READ MODELS

The user can now use a new shell to login and change his/her email address.
To accommodate these features, read models will be used by the UI. For the
available commands in the new shell see SHELL-COMMANDS.txt or run 'help'.

Because we will be using read models, the Application no longer provides an
interface to fetch a user.

There is also virtual mail server with all sent emails. This mail server is not
part of the domain and is already implemented.

Create the necessary listeners in the UI, and register them with the application.
These listeners should keep the DTO's in the UI in sync. Classes in the UI to
change are in the package 'axon.ui.user'.
All tests should run green at the end of this exercise.

Tips:
 - Register the listener by Autowiring the Application in the listener and
    register itself it in a @PostConstruct annotated method.