package edu.cnm.deepdive.megamillions.view;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import edu.cnm.deepdive.megamillions.R;
import java.util.Arrays;
import java.util.List;

public class PickAdapter extends RecyclerView.Adapter<PickAdapter.Holder> {

  private Context context;
  private List<int[]> picks;

  //TODO MOdify to take ListPickWithNumbers>.
  public PickAdapter(Context context, List<int[]> picks) {
    this.context = context;
    this.picks = picks;

  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(context).inflate(R.layout.pick_item, viewGroup, false);
    if (i % 2 == 1) {
      view.setBackgroundColor(Color.argb(16, 0, 0, 0));
    }
    return new Holder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind();
  }

  @Override
  public int getItemCount() {
    return picks.size();
  }

  public class Holder extends RecyclerView.ViewHolder
      implements View.OnCreateContextMenuListener {

    private static final int PICK_LENGTH = 6;

    private TextView[] numbers;

    public Holder(@NonNull View view) {
      super(view);
      view.setOnCreateContextMenuListener(this);
      numbers = new TextView[PICK_LENGTH];
      for (int i = 0; i < PICK_LENGTH; i++) {
        int id = context.getResources().getIdentifier("num_" + i, "id", context.getPackageName());
        numbers[i] = view.findViewById(id);
      }
    }

    private void bind() {
      int[] numbers = picks.get(getAdapterPosition());
      for (int i = 0; i < numbers.length; i++) {
        this.numbers[i].setText(Integer.toString(numbers[i]));
      }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
      menu.add(R.string.delete_pick).setOnMenuItemClickListener((item) -> {
        picks.remove(getAdapterPosition());
        notifyItemRemoved(getAdapterPosition());
        return true;
      });
    }

  }
// TODO Create deleteTask that takes a Pickwithnumbers instance
}
