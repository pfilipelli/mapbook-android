/*
 *  Copyright 2017 Esri
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *  *
 *  * For additional information, contact:
 *  * Environmental Systems Research Institute, Inc.
 *  * Attn: Contracts Dept
 *  * 380 New York Street
 *  * Redlands, California, USA 92373
 *  *
 *  * email: contracts@esri.com
 *  *
 *
 */

package com.esri.android.mapbook.map;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.esri.android.mapbook.R;
import com.esri.arcgisruntime.layers.Layer;

import java.util.List;

public class MapContentAdapter extends RecyclerView.Adapter<MapContentAdapter.MapRecycleViewContentHolder> {
  private List<Layer> mLayers ;


  public MapContentAdapter(){}

  public void setLayerList(List layers){

    mLayers = layers;

  }

  @Override public MapRecycleViewContentHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    View itemView = LayoutInflater.
        from(viewGroup.getContext()).
        inflate(R.layout.map_content, viewGroup, false);

    return new MapRecycleViewContentHolder(itemView);
  }

  @Override public void onBindViewHolder(final MapRecycleViewContentHolder holder, final int position) {
    final Layer layer = mLayers.get(position);
    holder.mapContentName.setText(layer.getName());

    boolean layerVisible = (layer.isVisible());
    holder.checkBox.setChecked(layerVisible);
    holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (layer.isVisible()){
          layer.setVisible(false);
        }else{
          layer.setVisible(true);
        }
      }
    });
  }

  @Override public int getItemCount() {
    if (mLayers == null){
      return 0;
    }else{
      return mLayers.size();
    }
  }

  public class MapRecycleViewContentHolder extends RecyclerView.ViewHolder{

    public final TextView mapContentName;
    public final CheckBox checkBox;

    public MapRecycleViewContentHolder(final View view){
      super(view);
      checkBox = (CheckBox) view.findViewById(R.id.cbLayer) ;
      mapContentName = (TextView) view.findViewById(R.id.txtMapContentName);
    }
  }
}