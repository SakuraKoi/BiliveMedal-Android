package sakura.kooi.android.bilivemedal.fragments.follow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import sakura.kooi.android.bilivemedal.R;
import sakura.kooi.android.bilivemedal.ui.MedalLogoView;

public class FollowFragment extends Fragment {

    private FollowViewModel followViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        followViewModel =
                ViewModelProviders.of(this).get(FollowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_follow, container, false);
        return root;
    }
}