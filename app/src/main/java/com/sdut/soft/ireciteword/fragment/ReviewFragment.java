package com.sdut.soft.ireciteword.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.sdut.soft.ireciteword.R;
import com.sdut.soft.ireciteword.ReviewActivity;
import com.sdut.soft.ireciteword.bean.User;
import com.sdut.soft.ireciteword.dao.WordDao;
import com.sdut.soft.ireciteword.user.UserService;
import com.sdut.soft.ireciteword.utils.SettingsUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReviewFragment extends android.support.v4.app.Fragment {
    UserService userService;

    @BindView(R.id.pie_chart)
    PieChart pieChart;
    @BindView(R.id.tv_last)
    TextView tvLast;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    private void initView() {
        userService = new UserService(getContext());
        User user = userService.currentUser();
        tvLast.setText(
                String.format("还剩%s个单词没有复习！！！"
                                    ,String.valueOf(user.getRcindex()-user.getRvindex())));
        showPieChart(pieChart, getPieChartData());
    }
    private List<PieEntry> getPieChartData() {
        List<PieEntry> mPie = new ArrayList<>();
        WordDao wordDao = new WordDao(getContext());
        User user = userService.currentUser();
        int total = wordDao.getTotalCnt(SettingsUtils.getMeta(getContext()));
        long rvCnt = user.getRvindex();
        long rcCnt = user.getRcindex();
        float review =   1.0f* rvCnt / total;
        float unReview = 1.0f* (rcCnt - rvCnt) / total;
        float newWord =  1.0f* (total - rcCnt) / total;
        mPie.add(new PieEntry(review,"已复习"));
        mPie.add(new PieEntry(unReview,"未复习"));
        mPie.add(new PieEntry(newWord,"未学习"));
        return mPie;
    }

    private void showPieChart(PieChart pieChart, List<PieEntry> pieList) {
        PieDataSet dataSet = new PieDataSet(pieList,"Label");

        // 设置颜色list，让不同的块显示不同颜色，下面是我觉得不错的颜色集合，比较亮
        ArrayList<Integer> colors = new ArrayList<Integer>();
        int[] MATERIAL_COLORS = {
                Color.rgb(200, 172, 255)
        };
        for (int c : MATERIAL_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(c);
        }
        dataSet.setColors(colors);
        PieData pieData = new PieData(dataSet);

        // 设置描述，我设置了不显示，因为不好看，你也可以试试让它显示，真的不好看
        Description description = new Description();
        description.setEnabled(false);
        pieChart.setDescription(description);
        //设置半透明圆环的半径, 0为透明
        pieChart.setTransparentCircleRadius(0f);

        //设置初始旋转角度
        pieChart.setRotationAngle(-15);

        //数据连接线距图形片内部边界的距离，为百分数
        dataSet.setValueLinePart1OffsetPercentage(80f);

        //设置连接线的颜色
        dataSet.setValueLineColor(Color.LTGRAY);
        // 连接线在饼状图外面
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);

        // 设置饼块之间的间隔
        dataSet.setSliceSpace(1f);
        dataSet.setHighlightEnabled(true);
        // 不显示图例
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);

        // 和四周相隔一段距离,显示数据
        pieChart.setExtraOffsets(26, 5, 26, 5);

        // 设置pieChart图表是否可以手动旋转
        pieChart.setRotationEnabled(false);
        // 设置piecahrt图表点击Item高亮是否可用
        pieChart.setHighlightPerTapEnabled(true);
        // 设置pieChart图表展示动画效果，动画运行1.4秒结束
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        //设置pieChart是否只显示饼图上百分比不显示文字
        pieChart.setDrawEntryLabels(true);
        //是否绘制PieChart内部中心文本
        pieChart.setDrawCenterText(false);
        // 绘制内容value，设置字体颜色大小
        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(10f);
        pieData.setValueTextColor(Color.DKGRAY);

        pieChart.setData(pieData);
        // 更新 piechart 视图
        pieChart.postInvalidate();
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    @OnClick(R.id.btn_review)
    public void review() {
        Intent intent = new Intent(getActivity(), ReviewActivity.class);
        startActivity(intent);
    }
}
