package com.example.mymusic;

import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final ArrayList<Image> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setData();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        CustomRecyclerAdapter adapter = new CustomRecyclerAdapter(this, images);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Здесь обрабатывайте событие нажатия на элемент
                // Например, можно открыть новый экран с деталями элемента
            }
        });
    }

    private void setData() {
        images.add(new Image("Nevermind", R.drawable.album1));
        images.add(new Image("Adore", R.drawable.album2));
        images.add(new Image("mycat", R.drawable.album3));
        images.add(new Image("nyancat", R.drawable.album4));
        images.add(new Image("cat1", R.drawable.album5));
        images.add(new Image("cat2", R.drawable.album6));
    }

    public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {
        private OnItemClickListener listener;

        private final LayoutInflater inflater;
        private final List<Image> images;
        private final Context context; // Добавьте поле для хранения контекста

        CustomRecyclerAdapter(Context context, List<Image> images) {
            this.context = context;
            this.images = images;
            this.inflater = LayoutInflater.from(context);
        }
        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CustomRecyclerAdapter.ViewHolder holder, int position) {
            Image image = images.get(position);
            holder.text.setText(image.getName());
            holder.image.setImageResource(image.getImageResource());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = holder.getAdapterPosition(); // Получаем актуальное положение элемента
                    if (listener != null) {
                        listener.onItemClick(itemPosition);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return images.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            final ImageView image;
            final TextView text;

            ViewHolder(View view) {
                super(view);
                image = view.findViewById(R.id.imageView);
                text = view.findViewById(R.id.textView);
            }
        }
    }

    public class Image {
        private String name; // название
        private int imageRes; // картинка

        Image(String name, int image) {
            this.name = name;
            this.imageRes = image;
        }

        public String getName() {
            return this.name;
        }

        public int getImageResource() {
            return this.imageRes;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}