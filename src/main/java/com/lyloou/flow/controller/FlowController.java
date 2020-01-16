package com.lyloou.flow.controller;

import com.lyloou.common.status.Result;
import com.lyloou.common.status.ResultHandler;
import com.lyloou.common.util.StringUtil;
import com.lyloou.common.util.TimeUtil;
import com.lyloou.flow.mapper.FlowMapper;
import com.lyloou.flow.model.flow.Flow;
import com.lyloou.flow.model.flow.FlowReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.lyloou.common.status.StatusCodeDict.*;
import static com.lyloou.flow.controller.ValidateHelper.validate;

/**
 * @author lyloou
 */
@RestController
@RequestMapping(path = "${apiVersion}/flow")
public class FlowController {

    private static final Logger logger = LoggerFactory.getLogger(FlowController.class);
    public static final int MAX_SYNC_NUMBER = 10;

    @Autowired
    private ResultHandler resultHandler;

    @Autowired
    FlowMapper flowMapper;

    @RequestMapping("/list")
    public Result list(@RequestParam(value = "user_id") Long userId,
                       @RequestParam(value = "limit", defaultValue = "10") int limit,
                       @RequestParam(value = "offset", defaultValue = "0") int offset) {
        List<Flow> flows = flowMapper.listFlow(userId, limit, offset);
        return resultHandler.dataResult(() -> COMMON_OK, flows);
    }

    @RequestMapping("/get")
    public Result get(
            @RequestHeader("Authorization") String authorization,
            @RequestParam(value = "user_id") Long userId,
            @RequestParam(value = "day", defaultValue = "") String day) {
        if (StringUtil.isEmpty(day)) {
            day = TimeUtil.today();
        }
        validate(authorization, userId);
        Flow flow = flowMapper.getFlow(userId, day);
        return resultHandler.dataResult(() -> COMMON_OK, flow);
    }


    @PostMapping("/sync")
    public Result sync(@RequestBody FlowReq flowReq) {
        int i = flowMapper.syncFlow(Flow.builder()
                .userId(flowReq.getUserId())
                .day(flowReq.getDay())
                .item(flowReq.getItem())
                .isArchived(flowReq.isArchived())
                .isDisabled(flowReq.isDisabled())
                .build());
        return resultHandler.msgResult(() -> i >= 0 ? COMMON_OK : COMMON_UNKNOWN);
    }

    @PostMapping("/batch_sync")
    public Result batchSync(@RequestParam(value = "user_id") Long userId,
                            @RequestBody List<FlowReq> flowReqs) {
        if (flowReqs.size() > MAX_SYNC_NUMBER) {
            return resultHandler.msgResult(() -> PARAM_BEYOND_QUANTITY_NUMBER);
        }
        int i = flowMapper.batchSyncFlow(flowReqs.stream()
                .map(flowReq -> Flow.builder().day(flowReq.getDay())
                        .item(flowReq.getItem())
                        .isArchived(flowReq.isArchived())
                        .isDisabled(flowReq.isDisabled())
                        .build())
                .collect(Collectors.toList())
        );
        return resultHandler.msgResult(() -> i >= 0 ? COMMON_OK : COMMON_UNKNOWN);
    }
}
