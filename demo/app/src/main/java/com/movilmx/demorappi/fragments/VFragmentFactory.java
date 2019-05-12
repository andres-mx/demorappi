package com.movilmx.demorappi.fragments;

import com.movilmx.core.ui.GenericFragment;
import com.movilmx.core.ui.GenericFragmentFactory;
import com.movilmx.core.videos.Videos;

public class VFragmentFactory extends GenericFragmentFactory {
    public VFragmentFactory(){}
    @Override
    public GenericFragment getMovieDetailFragment(int videoId, Videos video) {
        DetailVideoFragment detailVideoFragment = DetailVideoFragment.newInstance(videoId, video);
        return detailVideoFragment;
    }
}
