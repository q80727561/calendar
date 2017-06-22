package com.example.lol;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class CalendarView extends Fragment {
	protected final Calendar calendar;
	private final Locale locale;
	private ViewSwitcher calendarSwitcher;
	private TextView currentMonth;
	private CalendarAdapter calendarAdapter;
	public String date;
	 GridView calendarGrid;
	public CalendarView() {
		calendar = Calendar.getInstance();
		locale = Locale.getDefault();

	}
	public void refresh(){
		Calendar c = Calendar.getInstance();c.set(2012, 11, 12);
		calendarAdapter.selected = new CalendarAdapter.CalendarItem(c);
		calendarGrid.setAdapter(calendarAdapter);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final RelativeLayout calendarLayout = (RelativeLayout)inflater.inflate(R.layout.calendar, null);
		final GridView calendarDayGrid = (GridView)calendarLayout.findViewById(R.id.calendar_days_grid);
		final GestureDetector swipeDetector = new GestureDetector(getActivity(), new SwipeGesture(getActivity()));
		 calendarGrid = (GridView)calendarLayout.findViewById(R.id.calendar_grid);
		calendarSwitcher = (ViewSwitcher)calendarLayout.findViewById(R.id.calendar_switcher);
		currentMonth = (TextView)calendarLayout.findViewById(R.id.current_month);
		calendarAdapter = new CalendarAdapter(getActivity(), calendar);
		updateCurrentMonth();

		final TextView nextMonth = (TextView) calendarLayout.findViewById(R.id.next_month);
		nextMonth.setOnClickListener(new NextMonthClickListener());
		final TextView prevMonth = (TextView) calendarLayout.findViewById(R.id.previous_month);
		prevMonth.setOnClickListener(new PreviousMonthClickListener());


		calendarGrid.setAdapter(calendarAdapter);
		calendarGrid.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return swipeDetector.onTouchEvent(event);
			}
		});
		calendarDayGrid.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.day_item, getResources().getStringArray(R.array.days_array)));
		return calendarLayout;
	}

	protected void updateCurrentMonth() {
		calendarAdapter.refreshDays();
		currentMonth.setText(String.format(locale, "%tB", calendar) + " " + calendar.get(Calendar.YEAR));
	}
		public void onItemClick2( View view) {
			final TextView dayView = (TextView)view.findViewById(R.id.date);
			final CharSequence text = dayView.getText();
			if (text != null && !"".equals(text)) {
				calendarAdapter.setSelected(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), Integer.valueOf(String.valueOf(text)));
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月");
				String s=sdf.format(calendar.getTime());
				int tt= Integer.valueOf(String.valueOf(text));
				String.valueOf( tt);
				if(tt<10)
					date=s+0+ Integer.valueOf(String.valueOf(text));
				else
					date=s+ Integer.valueOf(String.valueOf(text));
		}
	}

	protected final void onNextMonth() {
		calendarSwitcher.setInAnimation(getActivity(), R.anim.in_from_right);
		calendarSwitcher.setOutAnimation(getActivity(), R.anim.out_to_left);
		calendarSwitcher.showNext();
		if (calendar.get(Calendar.MONTH) == Calendar.DECEMBER) {
			calendar.set((calendar.get(Calendar.YEAR) + 1), Calendar.JANUARY, 2);
		} else {
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
		}
		updateCurrentMonth();
	}

	protected final void onPreviousMonth() {
		calendarSwitcher.setInAnimation(getActivity(), R.anim.in_from_left);
		calendarSwitcher.setOutAnimation(getActivity(), R.anim.out_to_right);
		calendarSwitcher.showPrevious();
		if (calendar.get(Calendar.MONTH) == Calendar.JANUARY) {
			calendar.set((calendar.get(Calendar.YEAR) - 1), Calendar.DECEMBER, 2);
		} else {
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)-1);
		}
		updateCurrentMonth();
	}

	private final class NextMonthClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			onNextMonth();
		}
	}

	private final class PreviousMonthClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			onPreviousMonth();
		}
	}

	private final class SwipeGesture extends SimpleOnGestureListener {
		private final int swipeMinDistance;
		private final int swipeThresholdVelocity;

		public SwipeGesture(Context context) {
			final ViewConfiguration viewConfig = ViewConfiguration.get(context);
			swipeMinDistance = viewConfig.getScaledTouchSlop();
			swipeThresholdVelocity = viewConfig.getScaledMinimumFlingVelocity();
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
	        if (e1.getX() - e2.getX() > swipeMinDistance && Math.abs(velocityX) > swipeThresholdVelocity) {
	            onNextMonth();
	        }  else if (e2.getX() - e1.getX() > swipeMinDistance && Math.abs(velocityX) > swipeThresholdVelocity) {
	            onPreviousMonth();
	        }
	        return false;
		}
	}

}
