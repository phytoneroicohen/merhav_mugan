import android.view.View;
import android.widget.TextView;

public class myHolder {
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
    TextView idView,nameView,adsressView,ageView,gradesView;
    public myHolder(@NonNull View itemView){
        super(itemView);
        idView=itemView.findViewById(R.id.ID);
        nameView=itemView.findViewById(R.id.name);
        adsressView=itemView.findViewById(R.id.address);
        ageView=itemView.findViewById(R.id.age);
        gradesView=itemView.findViewById(R.id.grades);

}
