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