# android_responsiveLayout_testApp
Bare minimum test app rearranging views in a responsive way.

The 4 views change their position and size according to a set of layout constraints.
The vertical constraints for the views are set relative to a horizontal guideline. The seekbar value adusts the horizontal position of this guideline, and the set of constraints then adjusts the views to fit above and below the guideline.

![alt-text](screenCast.gif)

The key element is the combination of the 2 fields in the layout file:
```xml
<ImageView
...
android:layout_width="0dp"
app:layout_constraintWidth_default="wrap"
...
/>
```

as explained [here](https://developer.android.com/training/constraint-layout/#adjust-the-view-size)
