package com.codeinger.sadeb_employee_app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codeinger.sadeb_employee_app.R;
import com.codeinger.sadeb_employee_app.activity.MainActivity;
import com.codeinger.sadeb_employee_app.databinding.ItemHomeBinding;
import com.codeinger.sadeb_employee_app.network.model.userreq.ResultItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllServiceReqAdapter extends RecyclerView.Adapter<AllServiceReqAdapter.AllServiceReqAdapter_View> {

    private final List<ResultItem> list;
    private final Status status;
    Context context;

    public AllServiceReqAdapter(Context context,List<ResultItem> list,Status status) {
        this.list=list;
        this.status = status;
        this.context = context;
    }

    @NonNull
    @Override
    public AllServiceReqAdapter_View onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHomeBinding binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new AllServiceReqAdapter_View(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AllServiceReqAdapter_View holder, int position) {

        holder.binding.tvName.setText(list.get(position).getServiceName());//getUserName());

        holder.binding.tvStartTime.setText(String.format(context.getString(R.string.start), list.get(position).getStartTime()));
        holder.binding.tvEndTime.setText(String.format(context.getString(R.string.end), list.get(position).getEndTime()));
//        holder.binding.tvStartTime.setText("start :- " + list.get(position).getStartTime());
//        holder.binding.tvEndTime.setText("end :- " + list.get(position).getEndTime());
        holder.binding.tvReqType.setText(list.get(position).getUserName());//getServiceName());
        holder.binding.tvNoOfBooking.setText(list.get(position).getDate());//getMobile());
        holder.binding.tvWant.setText(list.get(position).getEmail());
        holder.binding.tvEmail.setText(list.get(position).getMobile());//getDate());
        Picasso.get().load(list.get(position).getImage()).placeholder(R.drawable.user_placeholder).into(holder.binding.roundedUserImage);

        if (list.get(position).getStatus().equals("Cancel") || list.get(position).getStatus().equals("Cancelado")){
            holder.binding.tvStatus.setVisibility(View.VISIBLE);
            holder.binding.tvStatus.setText(R.string.cancel);
            holder.binding.tvStatus.setTextColor(Color.parseColor("#ED0000"));
            holder.binding.tvStatus.setBackgroundResource(R.drawable.bg_stroke_red);
            holder.binding.llAction.setVisibility(View.GONE);
            holder.binding.tvCompleted.setVisibility(View.VISIBLE);
        }else if (list.get(position).getStatus().equals("Accept") || list.get(position).getStatus().equals("Aceptado")){
            holder.binding.tvStatus.setBackgroundResource(R.drawable.bg_stroke_blue);
            holder.binding.tvStatus.setVisibility(View.GONE);
            holder.binding.tvStatus.setText(R.string.complete);
            holder.binding.tvStatus.setTextColor(Color.parseColor("#0053B4"));
            holder.binding.tvCompleted.setVisibility(View.VISIBLE);
            holder.binding.llAction.setVisibility(View.GONE);
        }else if (list.get(position).getStatus().equals("Complete") || list.get(position).getStatus().equals("Completo")){

            holder.binding.tvStatus.setBackgroundResource(R.drawable.bg_stroke_blue);
            holder.binding.tvStatus.setVisibility(View.VISIBLE);
            holder.binding.tvStatus.setText(R.string.completed);
            holder.binding.tvStatus.setTextColor(Color.parseColor("#0053B4"));
            holder.binding.tvCompleted.setVisibility(View.GONE);
            holder.binding.llAction.setVisibility(View.GONE);
        } else{
            holder.binding.tvStatus.setVisibility(View.GONE);
            holder.binding.llAction.setVisibility(View.VISIBLE);
            holder.binding.tvCompleted.setVisibility(View.GONE);
        }

        holder.binding.tvAccept.setOnClickListener(v -> {
            status.acceptcontrol(position, () -> {
//                holder.binding.tvStatus.setText(R.string.accepted);
//                holder.binding.tvStatus.setBackgroundResource(R.drawable.bg_storke_green);
//                holder.binding.tvStatus.setTextColor(Color.parseColor("#10B234"));
//                holder.binding.tvStatus.setVisibility(View.VISIBLE);
                holder.binding.tvDecline.setVisibility(View.GONE);
                holder.binding.tvAccept.setVisibility(View.GONE);
                holder.binding.tvCompleted.setVisibility(View.VISIBLE);
            });
        });

        holder.binding.tvDecline.setOnClickListener(v -> {
            status.declinetcontrol(position, () -> {
                holder.binding.tvStatus.setText(R.string.cancel);
                holder.binding.tvStatus.setBackgroundResource(R.drawable.bg_stroke_red);
                holder.binding.tvStatus.setTextColor(Color.parseColor("#ED0000"));
                holder.binding.tvStatus.setVisibility(View.VISIBLE);
                holder.binding.tvDecline.setVisibility(View.GONE);
                holder.binding.tvAccept.setVisibility(View.GONE);
                holder.binding.tvCompleted.setVisibility(View.GONE);

            });
        });

        holder.binding.tvCompleted.setOnClickListener(v ->
                {
                    status.completecontrol(position,()->
                            {
                                holder.binding.tvStatus.setText(R.string.completed);
                                holder.binding.tvStatus.setBackgroundResource(R.drawable.bg_stroke_blue);
                                holder.binding.tvStatus.setTextColor(Color.parseColor("#0053B4"));
                                holder.binding.tvStatus.setVisibility(View.VISIBLE);
                                holder.binding.tvDecline.setVisibility(View.GONE);
                                holder.binding.tvAccept.setVisibility(View.GONE);
                                holder.binding.tvCompleted.setVisibility(View.GONE);
                            }
                            );

                }
                );

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AllServiceReqAdapter_View extends RecyclerView.ViewHolder {
        private final ItemHomeBinding binding;

        public AllServiceReqAdapter_View(@NonNull ItemHomeBinding binding) {
            super(binding.getRoot());
            this.binding=binding;

        }
    }

    public interface Status {
        void acceptcontrol(int position, MainActivity.DoneCallback callback);
        void declinetcontrol(int position, MainActivity.DoneCallback callback);
        void completecontrol(int position, MainActivity.DoneCallback callback);
    }
}
