package net.teamfruit.mchelishield.asm;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import net.teamfruit.mchelishield.asm.lib.DescHelper;
import net.teamfruit.mchelishield.asm.lib.MethodMatcher;
import net.teamfruit.mchelishield.asm.lib.RefName;

public class MCH_MultiplayClientVisitor extends ClassVisitor {
	private static class StartSendImageDataMethodVisitor extends MethodVisitor {
		public StartSendImageDataMethodVisitor(final @Nullable MethodVisitor mv) {
			super(Opcodes.ASM5, mv);
		}

		@Override
		public void visitCode() {
			/*
			 * 0  invokestatic mcheli.multiplay.MCH_MultiplayClient.startSendImageData() : void [15]
			 * 3  return
			 */
			super.visitMethodInsn(Opcodes.INVOKESTATIC, "net/teamfruit/mchelishield/MCH_MultiplayClient", "startSendImageData", DescHelper.toDesc(void.class, new Object[0]), false);
			super.visitInsn(Opcodes.RETURN);
		}
	}

	private static class SendScreenShotMethodVisitor extends MethodVisitor {
		public SendScreenShotMethodVisitor(final @Nullable MethodVisitor mv) {
			super(Opcodes.ASM5, mv);
		}

		@Override
		public void visitCode() {
			/*
			 * 0  iload_0 [displayWidth]
			 * 1  iload_1 [displayHeight]
			 * 2  aload_2 [framebufferMc]
			 * 3  invokestatic mcheli.multiplay.MCH_MultiplayClient.sendScreenShot(int, int, net.minecraft.client.shader.Framebuffer) : void [21]
			 * 6  return
			 */
			super.visitVarInsn(Opcodes.ILOAD, 0);
			super.visitVarInsn(Opcodes.ILOAD, 1);
			super.visitVarInsn(Opcodes.ALOAD, 2);
			super.visitMethodInsn(Opcodes.INVOKESTATIC, "net/teamfruit/mchelishield/MCH_MultiplayClient", "sendScreenShot", DescHelper.toDesc(void.class, int.class, int.class, "net.minecraft.client.shader.Framebuffer"), false);
			super.visitInsn(Opcodes.RETURN);
		}
	}

	private static class ReadImageDataMethodVisitor extends MethodVisitor {
		public ReadImageDataMethodVisitor(final @Nullable MethodVisitor mv) {
			super(Opcodes.ASM5, mv);
		}

		@Override
		public void visitCode() {
			/*
			 * 0  aload_0 [dos]
			 * 1  invokestatic mcheli.multiplay.MCH_MultiplayClient.readImageData(java.io.DataOutputStream) : void [33]
			 * 4  return
			 */
			super.visitVarInsn(Opcodes.ALOAD, 0);
			super.visitMethodInsn(Opcodes.INVOKESTATIC, "net/teamfruit/mchelishield/MCH_MultiplayClient", "readImageData", DescHelper.toDesc(void.class, "java.io.DataOutputStream"), false);
			super.visitInsn(Opcodes.RETURN);
		}
	}

	private static class SendImageDataMethodVisitor extends MethodVisitor {
		public SendImageDataMethodVisitor(final @Nullable MethodVisitor mv) {
			super(Opcodes.ASM5, mv);
		}

		@Override
		public void visitCode() {
			/*
			 * 0  invokestatic mcheli.multiplay.MCH_MultiplayClient.sendImageData() : void [38]
			 * 3  return
			 */
			super.visitMethodInsn(Opcodes.INVOKESTATIC, "net/teamfruit/mchelishield/MCH_MultiplayClient", "sendImageData", DescHelper.toDesc(void.class, new Object[0]), false);
			super.visitInsn(Opcodes.RETURN);
		}
	}

	private static class GetPerDataMethodVisitor extends MethodVisitor {
		public GetPerDataMethodVisitor(final @Nullable MethodVisitor mv) {
			super(Opcodes.ASM5, mv);
		}

		@Override
		public void visitCode() {
			/*
			 * 0  invokestatic mcheli.multiplay.MCH_MultiplayClient.getPerData() : double [42]
			 * 3  dreturn
			 */
			super.visitMethodInsn(Opcodes.INVOKESTATIC, "net/teamfruit/mchelishield/MCH_MultiplayClient", "getPerData", DescHelper.toDesc(double.class, new Object[0]), false);
			super.visitInsn(Opcodes.DRETURN);
		}
	}

	private static class ReadModListMethodVisitor extends MethodVisitor {
		public ReadModListMethodVisitor(final @Nullable MethodVisitor mv) {
			super(Opcodes.ASM5, mv);
		}

		@Override
		public void visitCode() {
			/*
			 * 0  aload_0 [playerName]
			 * 1  invokestatic mcheli.multiplay.MCH_MultiplayClient.readModList(java.lang.String) : void [46]
			 * 4  return
			 */
			super.visitVarInsn(Opcodes.ALOAD, 0);
			super.visitMethodInsn(Opcodes.INVOKESTATIC, "net/teamfruit/mchelishield/MCH_MultiplayClient", "readModList", DescHelper.toDesc(void.class, "java.lang.String"), false);
			super.visitInsn(Opcodes.RETURN);
		}
	}

	private static class SendModsInfoMethodVisitor extends MethodVisitor {
		public SendModsInfoMethodVisitor(final @Nullable MethodVisitor mv) {
			super(Opcodes.ASM5, mv);
		}

		@Override
		public void visitCode() {
			/*
			 * 0  aload_0 [playerName]
			 * 1  iload_1 [id]
			 * 2  invokestatic mcheli.multiplay.MCH_MultiplayClient.sendModsInfo(java.lang.String, int) : void [52]
			 * 5  return
			 */
			super.visitVarInsn(Opcodes.ALOAD, 0);
			super.visitVarInsn(Opcodes.ILOAD, 1);
			super.visitMethodInsn(Opcodes.INVOKESTATIC, "net/teamfruit/mchelishield/MCH_MultiplayClient", "sendModsInfo", DescHelper.toDesc(void.class, "java.lang.String", int.class), false);
			super.visitInsn(Opcodes.RETURN);
		}
	}

	private final @Nonnull MethodMatcher startSendImageDataMatcher;
	private final @Nonnull MethodMatcher sendScreenShotMatcher;
	private final @Nonnull MethodMatcher readImageDataMatcher;
	private final @Nonnull MethodMatcher sendImageDataMatcher;
	private final @Nonnull MethodMatcher getPerDataMatcher;
	private final @Nonnull MethodMatcher readModListMatcher;
	private final @Nonnull MethodMatcher sendModsInfoMatcher;

	public MCH_MultiplayClientVisitor(final @Nonnull String obfClassName, final @Nonnull ClassVisitor cv) {
		super(Opcodes.ASM5, cv);
		this.startSendImageDataMatcher = new MethodMatcher(obfClassName, DescHelper.toDesc(void.class, new Object[0]), RefName.name("startSendImageData"));
		this.sendScreenShotMatcher = new MethodMatcher(obfClassName, DescHelper.toDesc(void.class, int.class, int.class, "net.minecraft.client.shader.Framebuffer"), RefName.name("sendScreenShot"));
		this.readImageDataMatcher = new MethodMatcher(obfClassName, DescHelper.toDesc(void.class, "java.io.DataOutputStream"), RefName.name("readImageData"));
		this.sendImageDataMatcher = new MethodMatcher(obfClassName, DescHelper.toDesc(void.class, new Object[0]), RefName.name("sendImageData"));
		this.getPerDataMatcher = new MethodMatcher(obfClassName, DescHelper.toDesc(double.class, new Object[0]), RefName.name("getPerData"));
		this.readModListMatcher = new MethodMatcher(obfClassName, DescHelper.toDesc("java.lang.String", new Object[0]), RefName.name("readModList"));
		this.sendModsInfoMatcher = new MethodMatcher(obfClassName, DescHelper.toDesc(void.class, "java.lang.String", int.class), RefName.name("sendModsInfo"));
	}

	@Override
	public @Nullable MethodVisitor visitMethod(final int access, final @Nullable String name, final @Nullable String desc, final @Nullable String signature, final @Nullable String[] exceptions) {
		final MethodVisitor parent = super.visitMethod(access, name, desc, signature, exceptions);
		if (name==null||desc==null)
			return parent;
		else if (this.startSendImageDataMatcher.match(name, desc))
			return new StartSendImageDataMethodVisitor(parent);
		else if (this.sendScreenShotMatcher.match(name, desc))
			return new SendScreenShotMethodVisitor(parent);
		else if (this.readImageDataMatcher.match(name, desc))
			return new ReadImageDataMethodVisitor(parent);
		else if (this.sendImageDataMatcher.match(name, desc))
			return new SendImageDataMethodVisitor(parent);
		else if (this.getPerDataMatcher.match(name, desc))
			return new GetPerDataMethodVisitor(parent);
		else if (this.readModListMatcher.match(name, desc))
			return new ReadModListMethodVisitor(parent);
		else if (this.sendModsInfoMatcher.match(name, desc))
			return new SendModsInfoMethodVisitor(parent);
		else
			return parent;
	}
}