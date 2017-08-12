# Pre-work - *TODO APP*

**TODO APP** is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: **Meng Zhou**

Time spent: **5** hours spent in total

## User Stories

The following **required** functionality is completed:

* [ ] User can **successfully add and remove items** from the todo list
* [ ] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* [ ] User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

* [ ] Persist the todo items into shared preference
* [ ] Add support for completion due dates for todo items and display within ReycleView item
* [ ] Add support for selecting the priority of each todo item and display in ReycleView item with alpha images
* [ ] Add checkbox for marking todo status and display it within ReycleView
* [ ] Add touch animation for removing items
* [ ] Add empty background for todo list
* [ ] Tweak the style improving the UI / UX, play with colors, images or backgrounds

The following **additional** features are implemented:

* [ ] Use MVP architecture to separate presentation layer and data layer
* [ ] Use ReycleView instead of listview
* [ ] Use Fragment instead of new Activity for editing items
* [ ] Use FloatingActionButton instead of normal button
* [ ] Use Constrainlayout instead of Linerlayout and relativelayout
* [ ] Use DatePickerDialog and TimePickerDialog to set due date and time of Todo item. 
* [ ] Sort todo list by its creating time

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/usmeng/Todo-App/blob/master/screenshots/todo-demo.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Project Analysis

As part of your pre-work submission, please reflect on the app and answer the following questions below:

**Question 1:** "What are your reactions to the Android app development platform so far? Compare and contrast Android's approach to layouts and user interfaces in past platforms you've used."

**Answer:** [Design the app following the material design priciple, and implements it by using design support library,
RecycleView makes list more dynamic and interesting, Contrainlayout makes visual development happen. Especially by using MVP architecture, presentation layer and data layer seprareted, making it easier to testing, extend and maintain].

**Question 2:** "Take a moment to reflect on the `ArrayAdapter` used in your pre-work. How would you describe an adapter in this context and what is its function in Android? Why do you think the adapter is important? Explain the purpose of the `convertView` in the `getView` method of the `ArrayAdapter`."

**Answer:** [Sorry, I didn't use ArrayAdapter in my project. But I also could answer it. We can think adapter is an independent and portable component, within which data filled, its a data model in Android. Adapter sperated UI and data in a way. ConvertView is views where to display data, and its reuseable. The getview method is to get a View that displays the data at the specified position in the data set.]

## Notes

The challenge I encountered is how to design the MVP architecture to spearate presentation layer and data layer, and at the same time how to reduce the complexity of adding more classes and complicated APIs.

## License

    Copyright [2017] [Meng Zhou]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
