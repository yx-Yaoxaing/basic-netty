package com.cqnews.cloud.echo.coder;

import com.cqnews.cloud.echo.protocol.BaseProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import java.util.List;

/**
 * @author Administrator
 */
public class Decoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> list) throws Exception {
        // 确保至少有足够的数据可读
        if (in.readableBytes() < BaseProtocol.MIN_LENGTH) {
            return;
        }

        // 标记当前读取位置
        in.markReaderIndex();

        // 读取魔数
        byte[] magicNumber = new byte[BaseProtocol.MAGIC_NUMBER_LENGTH];
        in.readBytes(magicNumber);

        // 检查魔数是否匹配 （可以直接判断是否满足当前数据要求和协议要求）
        if (!checkMagicNumber(magicNumber)) {
            in.resetReaderIndex(); // 重置读取位置
            return;
        }



    }

    // 检查魔数是否匹配
    private boolean checkMagicNumber(byte[] magicNumber) {
        // 实现魔数匹配逻辑
        return java.util.Arrays.equals(magicNumber, BaseProtocol.MAGIC_NUMBER);
    }
}
