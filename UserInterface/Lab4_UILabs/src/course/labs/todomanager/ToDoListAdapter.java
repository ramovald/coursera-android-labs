package course.labs.todomanager;

import java.util.ArrayList;
import java.util.List;

import course.labs.todomanager.ToDoItem.Status;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ToDoListAdapter extends BaseAdapter {

	private final List<ToDoItem> mItems = new ArrayList<ToDoItem>();
	private final Context mContext;

	private static final String TAG = "Lab-UserInterface";

	public ToDoListAdapter(Context context) {

		mContext = context;

	}

	// Add a ToDoItem to the adapter
	// Notify observers that the data set has changed

	public void add(ToDoItem item) {

		mItems.add(item);
		notifyDataSetChanged();

	}

	// Clears the list adapter of all items.

	public void clear() {

		mItems.clear();
		notifyDataSetChanged();

	}

	// Returns the number of ToDoItems

	@Override
	public int getCount() {

		return mItems.size();

	}

	// Retrieve the number of ToDoItems

	@Override
	public Object getItem(int pos) {

		return mItems.get(pos);

	}

	// Get the ID for the ToDoItem
	// In this case it's just the position

	@Override
	public long getItemId(int pos) {

		return pos;

	}

	// Create a View for the ToDoItem at specified position
	// Remember to check whether convertView holds an already allocated View
	// before created a new View.
	// Consider using the ViewHolder pattern to make scrolling more efficient
	// See: http://developer.android.com/training/improving-layouts/smooth-scrolling.html
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = new ViewHolder();
		
		View layout = null;
		RelativeLayout itemLayout = new RelativeLayout(mContext);
		//LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    
	    //itemLayout.addView(layout);
	    
		
		// TODO - Get the current ToDoItem
		final ToDoItem toDoItem = (ToDoItem) this.getItem(position);


		// TODO - Inflate the View for this ToDoItem
		if (convertView == null) {
	        //layout = LayoutInflater.from(mContext).inflate(R.layout.todo_item, parent, false);
	    	itemLayout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.todo_item, parent, false);
	    	//convertView = inflater.inflate(R.layout.todo_item, parent, false);
	    } else {
	    	convertView = LayoutInflater.from(mContext).inflate(R.layout.todo_item, parent, false);
	    	itemLayout.addView(convertView);
	    }
		// from todo_item.xml
		//LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//RelativeLayout itemLayout = (RelativeLayout) inflater.inflate(R.layout.todo_item, null);
		//RelativeLayout itemLayout = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.todo_item, parent, false);
		
		
		// Fill in specific ToDoItem data
		// Remember that the data that goes in this View
		// corresponds to the user interface elements defined
		// in the layout file

		// TODO - Display Title in TextView
		final TextView titleView = (TextView) itemLayout.findViewById(R.id.titleView);
		titleView.setText(toDoItem.getTitle());
		viewHolder.setTitle(titleView);

		// TODO - Set up Status CheckBox
		final CheckBox statusView = (CheckBox) itemLayout.findViewById(R.id.statusCheckBox);
		if (toDoItem.getStatus() == Status.DONE) {
			statusView.setChecked(true);
		} else {
			statusView.setChecked(false);
		}
		
		viewHolder.setStatus(statusView);

		// TODO - Must also set up an OnCheckedChangeListener,
		// which is called when the user toggles the status checkbox

		statusView.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					   if (isChecked) {
						   toDoItem.setStatus(Status.DONE);
					   } else {
						   toDoItem.setStatus(Status.NOTDONE);   
					   }
					}
		});

		// TODO - Display Priority in a TextView
		final TextView priorityView = (TextView) itemLayout.findViewById(R.id.priorityView);
		priorityView.setText(toDoItem.getPriority().toString());
        viewHolder.setPriority(priorityView);

		// TODO - Display Time and Date.
		// Hint - use ToDoItem.FORMAT.format(toDoItem.getDate()) to get date and
		// time String
		final TextView dateView = (TextView) itemLayout.findViewById(R.id.dateView);
		dateView.setText(ToDoItem.FORMAT.format(toDoItem.getDate()));
		viewHolder.setTimeAndDate(dateView);
		
		itemLayout.setTag(viewHolder);
		
		// Return the View you just created
		return itemLayout;

	}
}

class ViewHolder {
	   TextView title;
	   CheckBox status;
	   TextView priority;
	   TextView timeAndDate;
	   
	public TextView getTitle() {
		return title;
	}
	public void setTitle(TextView title) {
		this.title = title;
	}
	public CheckBox getStatus() {
		return status;
	}
	public void setStatus(CheckBox status) {
		this.status = status;
	}
	public TextView getPriority() {
		return priority;
	}
	public void setPriority(TextView priority) {
		this.priority = priority;
	}
	public TextView getTimeAndDate() {
		return timeAndDate;
	}
	public void setTimeAndDate(TextView timeAndDate) {
		this.timeAndDate = timeAndDate;
	}
}
